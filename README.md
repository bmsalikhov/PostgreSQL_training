# Простой проект для освоения навыков работы с PostgreSQL

## Dependencies

Использовал [PostgreSQL JDBC Driver](https://mvnrepository.com/artifact/org.postgresql/postgresql/42.7.1)

## Что может делать приложение?
- создавать базу данных
- создавать таблицы
- связывать таблицы
- добавлять записи в таблицу
- выводить записи согласно заданию

## Структура БД
![image](https://github.com/bmsalikhov/PostgreSQL_training/assets/153372291/59709b33-b13c-4c87-afe1-b30c3612cf44)

## Packages
### data_source
Здесь хранится класс DbManager, в котором реализованы основные методы работы с БД.
#### Методы
- void createEmpsTable(String tableName) - создает таблицу с работниками
- void createSalariesTable(String tableName) - создает таблицу с зарплатами работников
- void insertEmployees(List<Employee> employeeList, String tableName) - добавляет работников в таблицу с работниками
- void insertSalaries(List<Salary> salaries, String tableName) - добавляет зарплаты в таблицу с зарплатами
- void selectAll(String tableName) - выводит id и имена всех работников
- void selectAllAgain(String tableName) - выводит фамилии и даты трудоустройства
- void selectAllOrder(String tableName) - выводит всех работников, отсортированных в обратном порядке по Id
- void selectSals(String empTableName, String empSalTableName) - выводит список сотрудников и их заплаты

### models
Здесь хранится класс Employee, созданный для создания сущности "работник" и класс Salary для создания сущности "зарплата" для последующей записи их в базу данных.
### main
Здесь хранится класс Main, в котором мы создаем список "работников" и "зарплаты" и далее тестируем описанные выше методы.
