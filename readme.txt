Инфраструктура для запуска приложения:
- JDK / JRE версий 8 и выше. 
- Apache Maven 3 и выше.
- СУБД MySQL Server версии 8 и выше.

Для запуска приложения необходимо скачать репозиторий командой:
git clone https://github.com/Nansy-S/RoomsWithBulbs.git

В приложении используются следующие свойства:
spring.datasource.url=jdbc:mysql://localhost:3306/rooms_with_bulbs
spring.datasource.username=admin
spring.datasource.password=admin

При необходимости изменить данные установленного MySQL Server, нужно изменить данные в файле 
/src/main/resources/application.properties
Также для запуска тестов - изменить данные в файле 
/src/test/resources/application-test.properties

При установке верных свойств, базы данных (основная и тестовая), создаются автоматически.

Перейдя в папку с приложением необходимо выполнить команду 
mvn package

Затем, для запуска приложения, команду
mvn exec:java

Для тестирования с локалхост было добавлено условие, чтобы страна для локалхоста была всегда Беларусь, 
т.к. в базе GeoLite2 нет сопоставления для локалхост.

