set foreign_key_checks = 0;
truncate department;
truncate employee;
truncate role;
insert into department(id, name) VALUES (1, 'Administration'),
                                        (2, 'Finance'),
                                        (3, 'Marketing'),
                                        (4, 'Software'),
                                        (5, 'Support');

insert into role(id, role) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');


insert into employee(id, address, cell_phone, email, home_phone, login, man_of_month, name, password, phone_number, picture, title, department_id, role_id)
            VALUES (1, '4914  Wilmar Farm Road', '240-305-8080', 'izt3yef74d@temporary-mail.net', '240-350-7677', 'user1', 1, 'Laurie C Lee', '$2y$12$InV1cM/QLBIqFg5EK0qMPeybnPuRh1Gk3d66RTzMLDAokBBYVp8Mq', '240-350-7677', 'https://randomuser.me/api/portraits/women/44.jpg', 'Architect', 4, 1),
                   (2, '2039  North Avenue', '402-764-6852', '995vkawvtge@temporary-mail.net', '402-822-5217', 'user2', null, 'Vickie B Campbell', '$2y$12$InV1cM/QLBIqFg5EK0qMPeybnPuRh1Gk3d66RTzMLDAokBBYVp8Mq', '402-764-6852', 'https://randomuser.me/api/portraits/women/79.jpg', 'Junior Dev', 4, 2),
                   (3, '3679  Dog Hill Lane', '785-731-6128', 'n407mc4q1ti@temporary-mail.net', '215-760-0786', 'user3', null, 'Monica D Tracy', '$2y$12$InV1cM/QLBIqFg5EK0qMPeybnPuRh1Gk3d66RTzMLDAokBBYVp8Mq', '785-731-6128', 'https://randomuser.me/api/portraits/women/79.jpg', 'Sales Girl', 3, 1);
set foreign_key_checks = 1;