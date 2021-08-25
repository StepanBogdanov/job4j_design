insert into role (name) values ('role 1');
insert into role (name) values ('role 2');
insert into role (name) values ('role 3');

insert into rules (rule) values ('rule 1');
insert into rules (rule) values ('rule 2');
insert into rules (rule) values ('rule 3');

insert into role_rules (role_id, rule_id) values (1, 1);
insert into role_rules (role_id, rule_id) values (1, 2);
insert into role_rules (role_id, rule_id) values (2, 2);
insert into role_rules (role_id, rule_id) values (2, 3);
insert into role_rules (role_id, rule_id) values (3, 3);

insert into users (name, role_id) values ('Ivan', 1);
insert into users (name, role_id) values ('Maksim', 2);
insert into users (name, role_id) values ('Igor', 3);

insert into categories(category) values ('high priority');
insert into categories(category) values ('low priority');

insert into states(state) values ('done');
insert into states(state) values ('processing');
insert into states(state) values ('queue');

insert into items (item, user_id, category_id, state_id) values ('item 1', 1, 1, 1);
insert into items (item, user_id, category_id, state_id) values ('item 2', 2, 1, 2);
insert into items (item, user_id, category_id, state_id) values ('item 3', 3, 2, 3);

insert into comments (comment, item_id) values ('comment 1', 1);
insert into comments (comment, item_id) values ('comment 1', 2);
insert into comments (comment, item_id) values ('comment 2', 3);

insert into attachs (name, item_id) values ('attach 1', 1);
insert into attachs (name, item_id) values ('attach 2', 2);
insert into attachs (name, item_id) values ('attach 3', 3);

