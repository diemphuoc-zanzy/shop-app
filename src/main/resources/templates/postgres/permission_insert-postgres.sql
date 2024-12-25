


INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('VIEW_CATEGORY_ADMIN', 'Permission to view ', '/categories/gets', 'POST', 'VIEW', 1);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('VIEW_CATEGORY_DETAIL_ADMIN', 'Permission to view ', '/categories/get/{id}', 'GET', 'VIEW', 1);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('CREATE_CATEGORY_ADMIN', 'Permission to create ', '/categories/create', 'POST', 'CREATE', 1);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('UPDATE_CATEGORY_ADMIN', 'Permission to update ', '/categories/update', 'PUT', 'UPDATE', 1);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('DELETE_CATEGORY_ADMIN', 'Permission to delete ', '/categories/delete', 'DELETE', 'DELETE', 1);

INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('VIEW_CATEGORY_"SELLER"', 'Permission to view ', '/categories/gets', 'POST', 'VIEW', 2);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('VIEW_CATEGORY_DETAIL_"SELLER"', 'Permission to view ', '/categories/get/{id}', 'GET', 'VIEW', 2);

INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('VIEW_CATEGORY_CUSTOMER', 'Permission to view ', '/categories/gets', 'POST', 'VIEW', 3);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('VIEW_CATEGORY_DETAIL_CUSTOMER', 'Permission to view ', '/categories/get/{id}', 'GET', 'VIEW', 3);

INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('VIEW_CATEGORY_MODERATOR', 'Permission to view ', '/categories/gets', 'POST', 'VIEW', 4);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('VIEW_CATEGORY_DETAIL_MODERATOR', 'Permission to view ', '/categories/get/{id}', 'GET', 'VIEW', 4);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('CREATE_CATEGORY_MODERATOR', 'Permission to create ', '/categories/create', 'POST', 'CREATE', 4);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('UPDATE_CATEGORY_MODERATOR', 'Permission to update ', '/categories/update', 'PUT', 'UPDATE', 4);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('DELETE_CATEGORY_MODERATOR', 'Permission to delete ', '/categories/delete', 'DELETE', 'DELETE', 4);


INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('REGISTER_USER_GUEST', 'Permission to register ', '/users/register', 'POST', 'CREATE', 5);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('LOGIN_USER_GUEST', 'Permission to login ', '/users/login', 'POST', 'UPDATE', 5);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('VIEW_CATEGORY_GUEST', 'Permission to view ', '/categories/gets', 'POST', 'VIEW', 5);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('VIEW_CATEGORY_DETAIL_GUEST', 'Permission to view ', '/categories/get/{id}', 'GET', 'VIEW', 5);
INSERT INTO permissions (name, description, resource, method, action, role_id) VALUES ('VIEW_PRODUCT_GUEST', 'Permission to view ', '/products/gets', 'POST', 'VIEW', 5);
