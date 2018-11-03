CREATE TABLE ers_reimbursement
(
    reimb_id NUMBER,
    reimb_amount NUMBER,
    reimb_submitted TIMESTAMP,
    reimb_resolved TIMESTAMP,
    reimb_description VARCHAR2(250),
    reimb_author NUMBER,
    reimb_resolver NUMBER,
    reimb_status_id NUMBER,
    reimb_type_id NUMBER,
    CONSTRAINT ers_reimbursement_pk PRIMARY KEY (reimb_id)
);

CREATE TABLE ers_users
(
    ers_user_id NUMBER,
    ers_username VARCHAR2(50) UNIQUE,
    ers_password VARCHAR2(50),
    user_first_name VARCHAR2(100),
    user_last_name VARCHAR2(100),
    user_email VARCHAR2(150) UNIQUE,
    user_role_id NUMBER,
    CONSTRAINT ers_users_pk PRIMARY KEY (ers_user_id)
);

CREATE TABLE ers_user_roles
(
    ers_user_role_id NUMBER,
    user_role VARCHAR(10),
    CONSTRAINT ers_user_roles_pk PRIMARY KEY (ers_user_role_id)
);

CREATE TABLE ers_reimbursement_type
(
    reimb_type_id NUMBER,
    reimb_type VARCHAR(10),
    CONSTRAINT reimb_type_pk PRIMARY KEY (reimb_type_id)
);

CREATE TABLE ers_reimbursement_status
(
    reimb_status_id NUMBER,
    reimb_status VARCHAR2(10),
    CONSTRAINT reimb_status_pk PRIMARY KEY (reimb_status_id)
);

--insert foreign keys on ers_reimbursement
ALTER TABLE ers_reimbursement ADD CONSTRAINT ers_users_fk_auth
    FOREIGN KEY (reimb_author)REFERENCES ers_users(ers_user_id)
    ON DELETE CASCADE;
    
ALTER TABLE ers_reimbursement ADD CONSTRAINT ers_users_fk_reslvr
    FOREIGN KEY (reimb_resolver)REFERENCES ers_users(ers_user_id)
    ON DELETE CASCADE;

ALTER TABLE ers_reimbursement ADD CONSTRAINT ers_reimbursement_status_fk
    FOREIGN KEY (reimb_status_id)REFERENCES ers_reimbursement_status (reimb_status_id)
    ON DELETE CASCADE;
    
ALTER TABLE ers_reimbursement ADD CONSTRAINT ers_reimbursement_type_fk
    FOREIGN KEY (reimb_type_id)REFERENCES ers_reimbursement_type (reimb_type_id)
    ON DELETE CASCADE;

--insert foreign keys on ers_users        
ALTER TABLE ers_users ADD CONSTRAINT user_roles_fk
    FOREIGN KEY (user_role_id)REFERENCES ers_user_roles (ers_user_role_id)
    ON DELETE CASCADE;
    
 -- Generate ID's and timestamps for reimbursements   
CREATE SEQUENCE ticket_id_seq START WITH 1;

CREATE OR REPLACE TRIGGER new_ticket
BEFORE INSERT ON ers_reimbursement
FOR EACH ROW
BEGIN
    SELECT ticket_id_seq.NEXTVAL
    INTO   :new.reimb_id
    FROM   dual;
    
    :new.reimb_submitted := CURRENT_TIMESTAMP;
END;
/


CREATE SEQUENCE new_employee_id_seq START WITH 1;

CREATE OR REPLACE TRIGGER new_employee
BEFORE INSERT ON ers_users
FOR EACH ROW
DECLARE user_id NUMBER;
BEGIN
    SELECT new_employee_id_seq.NEXTVAL
    INTO   :new.ers_user_id
    FROM   dual;
END;
/

CREATE OR REPLACE TRIGGER new_approval_or_deny
AFTER UPDATE ON ers_reimbursement
FOR EACH ROW
BEGIN
    INSERT INTO ers_reimbursement(reimb_resolved)
    VALUES (CURRENT_TIMESTAMP);
END;
/

-- Procedure to check if user exists in table
CREATE OR REPLACE PROCEDURE get_user(usrname IN ers_users.ers_username%TYPE, my_cursor OUT SYS_REFCURSOR)
AS
user_name VARCHAR2(50);
BEGIN
    SELECT ers_username INTO user_name FROM ers_users WHERE ers_username = usrname;
    
    OPEN my_cursor FOR
    user_name;
END;
/

INSERT INTO ers_users VALUES (1,'Gingerbread','Man','Ginger','Bread','ginger@gmail.com', 1);

INSERT INTO ers_reimbursement VALUES (1,1000,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Pencil is broken.',1,1,1,1);

-- Add roles to the user role table
INSERT INTO ers_user_roles VALUES(1, 'admin');
INSERT INTO ers_user_roles VALUES(2, 'student');

-- Add reimbursement types to the reimbursement type tables
INSERT INTO ers_reimbursement_type VALUES(1, 'loding');
INSERT INTO ers_reimbursement_type VALUES(2, 'travel');
INSERT INTO ers_reimbursement_type VALUES(3, 'food');
INSERT INTO ers_reimbursement_type VALUES(4, 'other');

-- Add status to reimbursement status
INSERT INTO ers_reimbursement_status VALUES(1, 'pending');
INSERT INTO ers_reimbursement_status VALUES(2, 'approved');
INSERT INTO ers_reimbursement_status VALUES(3, 'denied');

CREATE OR REPLACE TRIGGER set_reimbursement_resolved
BEFORE UPDATE ON ers_reimbursement
FOR EACH ROW
BEGIN
    :new.reimb_resolved := CURRENT_TIMESTAMP;
END;
/

commit;

Select * FROM ers_reimbursement;
Select * FROM ers_users;
