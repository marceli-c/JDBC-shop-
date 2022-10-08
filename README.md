# JDBC-shop-

<br>Project requires SQL database
<br>
<br>Commands to use in MySQL:

        create database sklep;
        use sklep;

        create table magazyn (ID int NOT NULL AUTO_INCREMENT,
        ilosc int,
        nazwa varchar(25),
        primary key(ID)
        );

        create table uzytkownicy ( ID int NOT NULL AUTO_INCREMENT,
        Login varchar(25),
        Email varchar(25),
        Haslo varchar(25),
        Admin tinyint(1),
        primary key(ID)
        );


        create table opisy (ID int not null auto_increment,
        nazwa varchar(25),
        opis varchar(1000),
        primary key(ID)
        );

        insert into uzytkownicy (Login,Haslo,Admin) values ('root','root',1);
        
<br>
<br>Simple GUI project with MySql database.
<br>Project features: adding/deleting/changing amount(purchase) items from GUI level 
<br>Register and login feature,
<br>Changing logged user information from GUI level.
<br>TODO: Adding photo as a blob to particular item.
<br> NOTE: Project is done for education purpose only, used photo of cat is not mine and its just for example.


![test 1](https://user-images.githubusercontent.com/112806657/194705183-f6c4f0ba-b45f-4525-9fef-c2eeccf702a5.PNG) 
![widok admina](https://user-images.githubusercontent.com/112806657/194705186-c3f2110e-e843-437c-bff8-c979cb1f6124.PNG)
![widok uzytkownika](https://user-images.githubusercontent.com/112806657/194705187-5cf4267f-d107-4cd9-838a-1d49849530c3.PNG)
![uzytkownik widok przedmiotu](https://user-images.githubusercontent.com/112806657/194705188-1573fa3a-c8b6-4a70-87ef-6f90b00aeb55.PNG)
![widok koszyk](https://user-images.githubusercontent.com/112806657/194705189-425c9a5e-2433-4fc7-870f-8f7e95cb4b24.PNG)


