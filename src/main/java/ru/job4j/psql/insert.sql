insert into roles(name) values ('role1');
insert into roles(name) values ('role2');

insert into users(name, role_id) values ('name1', 1);
insert into users(name, role_id) values ('name2', 1);
insert into users(name, role_id) values ('name3', 2);

insert into rules(rule) values ('rule1');
insert into rules(rule) values ('rule2');
insert into rules(rule) values ('rule3');

insert into role_rules(role_id, rule_id) values (1, 1);
insert into role_rules(role_id, rule_id) values (1, 2);
insert into role_rules(role_id, rule_id) values (2, 1);
insert into role_rules(role_id, rule_id) values (2, 3);

insert into categories(category) values (1);
insert into categories(category) values (2);
insert into categories(category) values (3);

insert into states(state) values ('state1');
insert into states(state) values ('state2');

insert into items(name, user_id, category_id, state_id) values ('name1', 1, 1, 1);
insert into items(name, user_id, category_id, state_id) values ('name2', 2, 1, 2);
insert into items(name, user_id, category_id, state_id) values ('name3', 2, 2, 2);
insert into items(name, user_id, category_id, state_id) values ('name4', 3, 3, 1);

insert into comments(comment, item_id) values ('comment1', 1);
insert into comments(comment, item_id) values ('comment2', 2);
insert into comments(comment, item_id) values ('comment3', 3);
insert into comments(comment, item_id) values ('comment4', 3);
insert into comments(comment, item_id) values ('comment5', 4);

insert into attachs(path, item_id) values ('path1', 2);
insert into attachs(path, item_id) values ('path2', 3);


