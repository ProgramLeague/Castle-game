CREATE TABLE ROOM(
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  disc TEXT, welc TEXT,
  boss TEXT,blood INTEGER,
  strike INTEGER, defence INTEGER,
  exp INTEGER, die TEXT
);
INSERT INTO ROOM(id, disc, boss, blood, strike, defence, exp, die) VALUES (
  0, '�Ǳ���','Ӣ����С͵ͷĿ', 200,25,10,15, 'С͵ͷĿ��Ǯȫ�������ˣ�'                        -- 0
);
INSERT INTO ROOM(disc, welc, boss, blood, strike, defence, exp, die) VALUES (
  'һ¥����','��ӭ�����Ǳ���','��ò�Ĵ��þ���',100,15,12,8,'���þ����������ˣ�'              -- 1
);
INSERT INTO ROOM(disc, welc, boss, blood, strike, defence, exp, die) VALUES (
  'С�ư�','һ��ɾ���Ʈ����','�����ľư���å', 150,10,5,5,'�ư���å�����ˣ�'                  -- 2
);
INSERT INTO ROOM(disc, welc, boss, blood, strike, defence, exp, die) VALUES (
  '�鷿','��������պ�Ũ��' , '���ŵĶ�����',100,7,5,3,'�����˵���������ˣ�'                -- 3
);
INSERT INTO ROOM(disc, welc, boss, blood, strike, defence, exp, die) VALUES (
  '�ù�', '��Χ�ɾ����ࡣ', '�ɰ���Ů��', 10,6,3,2,'Ů�ͱ����Ƶ��ˣ�'                          -- 4
);
INSERT INTO ROOM(disc, boss, blood, strike, defence, exp, die) VALUES (
  '��¥˯��','�����Ĺܼ�', 300,20,5,25,'�ܼ��˽֡����������Ƶ��ˣ�'                            -- 5
);
INSERT INTO ROOM(disc, boss, blood, strike, defence, exp, die) VALUES (
  '��һ¥','��ֵ�����',200,30,15,25,'�������վ����һ���������˵�Ů������'                    -- 6
);
INSERT INTO ROOM(disc, boss, blood, strike, defence, exp, die) VALUES (
  '����¥','���Ű�������ʿ',100,50,35,35,'��ʿ���·����ˣ�'                                    -- 7
);
INSERT INTO ROOM(disc, boss, blood, strike, defence, exp, die) VALUES (
  '����¥','�����׵�սʿ',300,30,25,45,'սʿ���Լ����ˣ�'                                  -- 8
);
INSERT INTO ROOM(disc, boss, blood, strike, defence, exp, die) VALUES (
  '����¥','�ֽ�����ʿ',400,40,35,60,'��ʿ�Ľ����ˣ�'                                          -- 9
);
INSERT INTO ROOM(disc) VALUES (
  '��¥��̨'                                                                                   -- 10
);
INSERT INTO ROOM(disc, boss, blood, strike, defence, exp, die) VALUES (
  '�Ǳ������t����', '�t��������',150, 20, 2, 20, '���������ˣ�'                                -- 11
);
INSERT INTO ROOM(disc, boss, blood, strike, defence, exp) VALUES (
  '��С��', '�ֱ�С���',100,30,1,20                                                         -- 12
);
INSERT INTO ROOM(disc, welc, boss, blood, strike, defence, exp, die) VALUES (
  '�ճ������','��ӭ�����Ǳ����ߵ��ճ��壡', '���Ƶ�����',150,20,20,30 ,'����¶���˺��Ƶ�Ц��' -- 13
);
INSERT INTO ROOM(disc, boss, blood, strike, defence, exp, die) VALUES (
  '���ؿռ��̳','����',1000,150,100,200,'���������д�ſͻ��ˡ�����'                          -- 14
);
INSERT INTO ROOM(disc, boss, blood, strike, defence, exp, die) VALUES (
  '���ؿռ���','��',1000,150,100,200,'�������д��������ˡ�����'                              -- 15
);
INSERT INTO ROOM(disc,boss, blood, strike, defence, exp, die) VALUES (
  '���ؿռ䶫','�̲�',1000,150,100,200,'�̲�ȥ��ϰ�����ˡ�����'                                -- 16
);
INSERT INTO ROOM(disc,  boss, blood, strike, defence, exp, die) VALUES (
  '���ؿռ䱱','����',1000,150,100,200,'�������ڴ��͡�����'                                  -- 17
);
INSERT INTO ROOM(disc) VALUES (
  '�ճ������'                                                                                 -- 18
);
INSERT INTO ROOM(disc, welc, boss, blood, strike, defence, exp, die) VALUES (
  '�ճ������','��˲�䱻������ʥ����Ϣ��Ϲ�ˡ�' , '������ڵ���ʦ',200, 30, 20, 40, '��ʦ��������ڡ�' -- 19
);
INSERT INTO ROOM(disc, boss, blood, strike, defence, exp, die) VALUES (
  '���صľ�', '��ˮ���ܺ���',50, 10, 1, 5, '�ܺ��ӵ�ͷ���ܡ�'                                 -- 20
);
INSERT INTO ROOM(disc, welc, boss, blood, strike, defence, exp, die) VALUES (
  '����', '����ܳ�ʪ����ɭ�ֲ���', '���ܹ�',300, 80, 50, 70, '���ֱܹ������ˣ�'              -- 21
);
INSERT INTO ROOM(disc, welc, boss, blood, strike, defence, exp, die) VALUES (
  '���ױ�', '���ӳ�ʪ�ˡ�', '���ʹڵ����ܹ���',500, 100, 40, 100, '���ܹ����Ļʹڵ���������'  -- 22
);
INSERT INTO ROOM(disc, welc, boss, blood, strike, defence, exp, die) VALUES (
  '��������', '���������������䳱ʪ����Ϣ��', '��ͷ�Ƶ�̽�ռ�',400, 80, 30, 80, '̽�ռҵ�ͷ��û���ˣ�' -- 23
);

CREATE TABLE DIR(id INTEGER PRIMARY KEY AUTOINCREMENT, from_text TEXT, to_text TEXT);
INSERT INTO DIR(from_text, to_text) VALUES ('up', 'down');
INSERT INTO DIR(from_text, to_text) VALUES ('north', 'south');
INSERT INTO DIR(from_text, to_text) VALUES ('east', 'west');

CREATE TABLE MAP( id INTEGER PRIMARY KEY AUTOINCREMENT, fromid INTEGER, toid INTEGER, dir INTEGER);
INSERT INTO MAP(fromid, toid, dir) VALUES (1, 5, 1);
INSERT INTO MAP(fromid, toid, dir) VALUES (5, 10,1);
INSERT INTO MAP(fromid, toid, dir) VALUES (10,11,1);
INSERT INTO MAP(fromid, toid, dir) VALUES (6, 1, 1);
INSERT INTO MAP(fromid, toid, dir) VALUES (7, 6, 1);
INSERT INTO MAP(fromid, toid, dir) VALUES (8, 7, 1);
INSERT INTO MAP(fromid, toid, dir) VALUES (9, 8, 1);
INSERT INTO MAP(fromid, toid, dir) VALUES (20,21,1);
INSERT INTO MAP(fromid, toid, dir) VALUES (21,22,1);
INSERT INTO MAP(fromid, toid, dir) VALUES (22,23,1);
INSERT INTO MAP(fromid, toid, dir) VALUES (3, 0, 2);
INSERT INTO MAP(fromid, toid, dir) VALUES (20,19,2);
INSERT INTO MAP(fromid, toid, dir) VALUES (4, 1, 2);
INSERT INTO MAP(fromid, toid, dir) VALUES (14,17,2);
INSERT INTO MAP(fromid, toid, dir) VALUES (0, 1, 3);
INSERT INTO MAP(fromid, toid, dir) VALUES (2, 0, 3);
INSERT INTO MAP(fromid, toid, dir) VALUES (3, 4, 3);
INSERT INTO MAP(fromid, toid, dir) VALUES (12,2, 3);
INSERT INTO MAP(fromid, toid, dir) VALUES (13,12,3);
INSERT INTO MAP(fromid, toid, dir) VALUES (15,14,3);
INSERT INTO MAP(fromid, toid, dir) VALUES (14,16,3);
INSERT INTO MAP(fromid, toid, dir) VALUES (17,14,3);
INSERT INTO MAP(fromid, toid, dir) VALUES (18,13,3);
INSERT INTO MAP(fromid, toid, dir) VALUES (19,18,3);


SELECT * FROM ROOM ORDER BY id ASC;
SELECT * FROM DIR ORDER BY id ASC;
SELECT * FROM MAP ORDER BY id ASC;