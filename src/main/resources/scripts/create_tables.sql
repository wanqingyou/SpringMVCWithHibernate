create table customers(customer_id int primary key auto_increment, name varchar(30) not null, contact varchar(30) not null, address varchar(100) not null);

create table orders(order_id int primary key auto_increment, order_date date not null, amount numeric(10,2) not null, customer_id int not null, foreign key(customer_id) references customers(customer_id));

create table orderitem(orderitem_id int primary key auto_increment, amount numeric(10,2) not null, quantity int not null, product_id int not null, order_id int not null, foreign key(order_id) references orders(order_id));

create table product(product_id int primary key auto_increment, name varchar(50) not null, unitprice numeric(10,2) not null);