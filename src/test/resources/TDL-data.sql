INSERT INTO TO_DO_LIST (name, complete) VALUES ('foo', false);
INSERT INTO TO_DO_LIST (name, complete) VALUES ('bar', false);
INSERT INTO TO_DO_LIST (name, complete) VALUES ('lorem', false);
INSERT INTO TO_DO_LIST (name, complete) VALUES ('ipsum', false);

INSERT INTO ITEM (name, complete, TO_DO_LIST_ID) VALUES ('foo', false, 1);
INSERT INTO ITEM (name, complete, TO_DO_LIST_ID) VALUES ('bar', false, 2);
INSERT INTO ITEM (name, complete, TO_DO_LIST_ID) VALUES ('lorem', false, 3);
INSERT INTO ITEM (name, complete, TO_DO_LIST_ID) VALUES ('ipsum', false, 4);