INSERT INTO "public"."action_templates" ("id", "description", "url") VALUES (1, 'Go to next user page', 'next_page');
INSERT INTO "public"."action_templates" ("id", "description", "url") VALUES (2, 'Jump to user page with number', 'set_page');
INSERT INTO "public"."action_templates" ("id", "description", "url") VALUES (3, 'Set user location', 'set_location');
INSERT INTO "public"."action_templates" ("id", "description", "url") VALUES (4, 'Show ticket panel on user page', 'show_tickets');
INSERT INTO "public"."action_templates" ("id", "description", "url") VALUES (5, 'Hide ticket panel on user page', 'hide_tickets');
INSERT INTO "public"."action_templates" ("id", "description", "url") VALUES (6, 'Grant ticket to user', 'grant_ticket');
INSERT INTO "public"."action_templates" ("id", "description", "url") VALUES (7, 'Open football for user', 'open_football');
INSERT INTO "public"."action_templates" ("id", "description", "url") VALUES (8, 'Open cartoon for user', 'open_cartoon');
INSERT INTO "public"."action_templates" ("id", "description", "url") VALUES (9, 'Set loyalty points', 'set_loyalty_point');

INSERT INTO "public"."url_params" ("id", "name") VALUES (1, 'number_page');
INSERT INTO "public"."url_params" ("id", "name") VALUES (2, 'name_location');
INSERT INTO "public"."url_params" ("id", "name") VALUES (3, 'receive_user');
INSERT INTO "public"."url_params" ("id", "name") VALUES (4, 'count_points');

INSERT INTO "public"."action_param" ("id", "id_action", "id_param") VALUES (1, 2, 1);
INSERT INTO "public"."action_param" ("id", "id_action", "id_param") VALUES (2, 3, 2);
INSERT INTO "public"."action_param" ("id", "id_action", "id_param") VALUES (3, 6, 3);
INSERT INTO "public"."action_param" ("id", "id_action", "id_param") VALUES (4, 9, 4);

INSERT INTO "public"."locations" ("id", "location_name") VALUES (1, 'london');
INSERT INTO "public"."locations" ("id", "location_name") VALUES (2, 'dubai');
INSERT INTO "public"."locations" ("id", "location_name") VALUES (3, 'rome');

INSERT INTO "public"."elements" ("id", "block_name", "param") VALUES (1, 'time_block', 'left');
INSERT INTO "public"."elements" ("id", "block_name", "param") VALUES (2, 'time_block', 'right');
INSERT INTO "public"."elements" ("id", "block_name", "param") VALUES (3, 'scroll_image', '1 Lock Screen');
INSERT INTO "public"."elements" ("id", "block_name", "param") VALUES (4, 'scroll_image', '2 Lock Screen');
INSERT INTO "public"."elements" ("id", "block_name", "param") VALUES (5, 'scroll_image', '3 Media Center');
INSERT INTO "public"."elements" ("id", "block_name", "param") VALUES (6, 'scroll_image', '5 Match page');
INSERT INTO "public"."elements" ("id", "block_name", "param") VALUES (7, 'loyalty_block', '365');
INSERT INTO "public"."elements" ("id", "block_name", "param") VALUES (8, 'scroll_image_video', '10 Media Center - Pass recieved');
INSERT INTO "public"."elements" ("id", "block_name", "param") VALUES (9, 'score_block', '0 : 0');

INSERT INTO "public"."pages" ("id") VALUES (1);
INSERT INTO "public"."pages" ("id") VALUES (2);
INSERT INTO "public"."pages" ("id") VALUES (3);
INSERT INTO "public"."pages" ("id") VALUES (4);
INSERT INTO "public"."pages" ("id") VALUES (5);

INSERT INTO "public"."page_element" ("id_page", "id_page_element", "id") VALUES (1, 2, 1);
INSERT INTO "public"."page_element" ("id_page", "id_page_element", "id") VALUES (1, 3, 2);
INSERT INTO "public"."page_element" ("id_page", "id_page_element", "id") VALUES (2, 1, 3);
INSERT INTO "public"."page_element" ("id_page", "id_page_element", "id") VALUES (2, 4, 4);
INSERT INTO "public"."page_element" ("id_page", "id_page_element", "id") VALUES (2, 9, 5);
INSERT INTO "public"."page_element" ("id_page", "id_page_element", "id") VALUES (2, 7, 6);
INSERT INTO "public"."page_element" ("id_page", "id_page_element", "id") VALUES (3, 5, 7);
INSERT INTO "public"."page_element" ("id_page", "id_page_element", "id") VALUES (3, 7, 8);
INSERT INTO "public"."page_element" ("id_page", "id_page_element", "id") VALUES (4, 6, 9);
INSERT INTO "public"."page_element" ("id_page", "id_page_element", "id") VALUES (4, 7, 10);
INSERT INTO "public"."page_element" ("id_page", "id_page_element", "id") VALUES (5, 8, 11);


INSERT INTO "public"."users" ("id", "first_name", "last_name", "count_tickets", "loyalty_points", "login", "location_id") VALUES (1, 'Rick', 'Boyle', 6, 365, 'ricky', 1);
INSERT INTO "public"."users" ("id", "first_name", "last_name", "count_tickets", "loyalty_points", "login", "location_id") VALUES (2, 'Sam', 'Boyle', 1, 365, 'sam', 1);


INSERT INTO "public"."user_page" ("id_user", "id_page", "id") VALUES (1, 1, 1);
INSERT INTO "public"."user_page" ("id_user", "id_page", "id") VALUES (1, 2, 2);
INSERT INTO "public"."user_page" ("id_user", "id_page", "id") VALUES (1, 3, 3);
INSERT INTO "public"."user_page" ("id_user", "id_page", "id") VALUES (1, 4, 4);
INSERT INTO "public"."user_page" ("id_user", "id_page", "id") VALUES (2, 5, 5);
