create table carts
(
CART_ID int not null primary key,
USER_ID int not null,
 foreign key(USER_ID) references users(id)
);