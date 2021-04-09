CREATE SEQUENCE USER_SEQUENCE
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO CYCLE;

CREATE SEQUENCE COURSE_SEQUENCE
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO CYCLE;

CREATE SEQUENCE STUDENT_SEQUENCE
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO CYCLE;

CREATE SEQUENCE COURSE_COORDINATOR_SEQUENCE
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO CYCLE;

CREATE SEQUENCE RECEIVING_INSTITUTION_SEQUENCE
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO CYCLE;

CREATE SEQUENCE SENDING_INSTITUTION_SEQUENCE
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO CYCLE;

CREATE SEQUENCE SENDING_INSTITUTION_COORDINATOR_SEQUENCE
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO CYCLE;

CREATE TABLE USERS
(
    ID                     bigint DEFAULT NEXTVAL('USER_SEQUENCE') PRIMARY KEY,
    FIRST_NAME             varchar(255)        NOT NULL,
    LAST_NAME              varchar(255)        NOT NULL,
    EMAIL                  varchar(255) UNIQUE NOT NULL,
    PASSWORD               varchar(255)        NOT NULL,
    LAST_PASSWORD_MODIFIED date,
    STATUS                 varchar(255)        NOT NULL,
    ROLE                   varchar(255)        NOT NULL,
    PHOTO_BLOB             bytea,
    PHOTO_CONTENT_LENGTH   int,
    PHOTO_CONTENT_TYPE     varchar(255)
);

CREATE TABLE STUDENTS
(
    ID                                      bigint DEFAULT NEXTVAL('STUDENT_SEQUENCE') PRIMARY KEY,
    EMAIL                                   varchar(255) UNIQUE NOT NULL,
    MATRICULATION_NUMBER                    bigint              NOT NULL,
    ACADEMIC_YEAR                           varchar(255)        NOT NULL,
    FIELD_OF_STUDY                          varchar(255)        NOT NULL,
    DEPARTMENT                              varchar(255)        NOT NULL,
    DEGREE_OF_STUDY                         varchar(255)        NOT NULL,
    SEMESTER                                varchar(255)        NOT NULL,
    DEPARTMENTAL_COORDINATOR_ACADEMIC_TITLE varchar(255)        NOT NULL,
    DEPARTMENTAL_COORDINATOR_FIRST_NAME     varchar(255)        NOT NULL,
    DEPARTMENTAL_COORDINATOR_LAST_NAME      varchar(255)        NOT NULL,
    DEPARTMENTAL_COORDINATOR_PHONE          varchar(255)        NOT NULL,
    DEPARTMENTAL_COORDINATOR_FAX            varchar(255)        NOT NULL,
    DEPARTMENTAL_COORDINATOR_EMAIL          varchar(255)        NOT NULL,
    FIRST_NAME                              varchar(255)        NOT NULL,
    LAST_NAME                               varchar(255)        NOT NULL,
    DATE_OF_BIRTH                           date                NOT NULL,
    PERIOD_OF_STUDY_FROM                    date                NOT NULL,
    PERIOD_OF_STUDY_UNTIL                   date                NOT NULL,
    PLACE_OF_BIRTH                          varchar(255)        NOT NULL,
    NATIONALITY                             varchar(255)        NOT NULL,
    CURRENT_ADDRESS                         varchar(255)        NOT NULL,
    PHONE                                   varchar(255)        NOT NULL,
    SEX                                     varchar(255)        NOT NULL,
    STUDY_CYCLE                             varchar(255)        NOT NULL,
    PHOTO_BLOB                              bytea
);

CREATE TABLE COURSES
(
    ID               bigint DEFAULT NEXTVAL('COURSE_SEQUENCE') PRIMARY KEY,
    UNIT_CODE        varchar(255) NOT NULL,
    NAME             varchar(255) NOT NULL,
    DURATION_OF_UNIT varchar(255) NOT NULL,
    CREDITS          bigint       NOT NULL,
    WHETHER_ACTIVE   boolean,
    SEMESTER         varchar(255) NOT NULL,
    DEPARTMENT       varchar(255) NOT NULL,
    NUMBER_OF_HOURS  varchar(255) NOT NULL,
    STUDENT_ID       bigint       NOT NULL,
    FOREIGN KEY (STUDENT_ID)
        REFERENCES STUDENTS (ID)
);

CREATE TABLE COURSE_COORDINATORS
(
    ID                                bigint DEFAULT NEXTVAL('COURSE_COORDINATOR_SEQUENCE') PRIMARY KEY,
    COURSE_COORDINATOR_EMAIL          varchar(255) UNIQUE NOT NULL,
    COURSE_COORDINATOR_ACADEMIC_TITLE varchar(255)        NOT NULL,
    COURSE_COORDINATOR_FIRST_NAME     varchar(255)        NOT NULL,
    COURSE_COORDINATOR_LAST_NAME      varchar(255)        NOT NULL,
    COURSE_COORDINATOR_PHONE          varchar(255)        NOT NULL,
    COURSE_COORDINATOR_FAX            varchar(255)        NOT NULL
);

CREATE TABLE RECEIVING_INSTITUTIONS
(
    ID                              bigint DEFAULT NEXTVAL('RECEIVING_INSTITUTION_SEQUENCE') PRIMARY KEY,
    RECEIVING_INSTITUTION_EMAIL     varchar(255) UNIQUE NOT NULL,
    RECEIVING_INSTITUTION_NAME      varchar(255)        NOT NULL,
    RECEIVING_INSTITUTION_PATRON    varchar(255)        NOT NULL,
    RECEIVING_INSTITUTION_WHERE     varchar(255)        NOT NULL,
    RECEIVING_INSTITUTION_ADDRESS   varchar(255)        NOT NULL,
    RECEIVING_INSTITUTION_POST_CODE varchar(255)        NOT NULL,
    RECEIVING_INSTITUTION_COUNTRY   varchar(255)        NOT NULL,
    RECEIVING_INSTITUTION_PHONE     varchar(255)        NOT NULL,
    RECEIVING_INSTITUTION_FAX       varchar(255)        NOT NULL
);

CREATE TABLE SENDING_INSTITUTIONS
(
    ID                            bigint DEFAULT NEXTVAL('SENDING_INSTITUTION_SEQUENCE') PRIMARY KEY,
    SENDING_INSTITUTION_EMAIL     varchar(255) UNIQUE NOT NULL,
    SENDING_INSTITUTION_NAME      varchar(255)        NOT NULL,
    SENDING_INSTITUTION_ADDRESS   varchar(255)        NOT NULL,
    SENDING_INSTITUTION_POST_CODE varchar(255)        NOT NULL,
    SENDING_INSTITUTION_COUNTRY   varchar(255)        NOT NULL,
    SENDING_INSTITUTION_PHONE     varchar(255)        NOT NULL,
    SENDING_INSTITUTION_FAX       varchar(255)        NOT NULL
);
