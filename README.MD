# Подготовка к запуску проекта в IDEA
+ Открыть конфигурацию проекта
+ Modify options
+ Operating System -> Environment variables
+ Вводим переменные
+ DATABASE_URL=your_db_connection
+ DATABASE_USERNAME=db_username
+ DATABASE_PASSWORD=db_password
+ Открываем вкладку Maven
+ Plugins -> apt -> apt:process
+ Нажимаем на папку с кружочком(Generate Sources and Update Folders for All Projects) в таб баре с основными функциями Maven
.Таким образом сгенерируются QEntities библиотеки QueryDsl
+ Запускаем проект
# Заполнение БД данными
1. http://localhost:8087/files/load/csv/customers c файлом в параметрах (customerstab.txt лист customers excel)
2. http://localhost:8087/files/load/csv/products c файлом в параметрах (productstab.txt лист products excel)
3. http://localhost:8087/files/load/csv/prices c файлом в параметрах (pricetab.txt лист prices excel)
4. http://localhost:8087/files/load/csv/actuals c файлом в параметрах (smalleractuals.txt укороченная версия лист actuals excel)

Таким образом мы заполним базу данных данными из эксель. Файлы для загрузки можно взять из гугл-диска.
https://drive.google.com/drive/folders/1YD3kDIKRlitBnYpm6X2pAyNrpTaqcw6i?usp=sharing

# Системное тестирование
Коллекция постман для запросов также  находится в гугл диске. 
После запуска проекта можно посмотреть сваггер - http://localhost:8087/swagger-ui/index.html .