# VK_Task

Rest api для перенаправления запросов на https://jsonplaceholder.typicode.com/

1) Обработчки GET, POST, PUT, DELETE для users, posts и albums
2) Авторизация с хранением пользователей в базе данных
3) Реализована ролевая модель с 4 ролями: ROLE_ADMIN, ROLE_USERS, ROLE_POSTS, ROLE_ALBUMS
4) Ведение аудита находится в папаке src, файл audit.log
5) Реализован inmemory кэш
6)  Есть rest api для создания новых пользователей  
По умолчанию создается 4 пользователя: admin, users, posts, albums Пароль такой же как и логин\
При создании пользвателя роли указываются через запятую: ROLE_ADMIN, ROLE_POSTS

7) Добавлен Swagger
8) Использованы flyway миграции для удобного создания базы данных

## Сборка и запуск

1. Склонируйте репозиторий:

    ```bash
    git clone https://github.com/blesssad/VK_Task
    ```

2. Далее необходимо поменять настройки для создания базы данных:
 - Перейдите в VK_Task/src/main/resources и отредактируйте application.properties. 
 - Смените username и password. 
 - Также можно поменять url, по умолчанию это postgres.

3. Соберите проект с помощью Maven в папке VK_Task:

    ```bash
    mvn clean install
    ```

4. Запустите приложение:

    ```bash
    java -jar target/vk_task-0.0.1-SNAPSHOT.jar
    ```

## Эндпоинты

- localhost:8080/api/posts
- localhost:8080/api/albums/
- localhost:8080/api/users/
- localhost:8080/newUser
- localhost:8080/login
- localhost:8080/logout
- localhost:8080/swagger-ui/index.html#/

## Postman и Swagger
**Postman**\
Перед тем как отправлять запросы к localhost:8080/api/** нужно залогиниться. То есть отправить POST запрос на localhost:8080/login с form-data: username и password. 

**Swagger** \
Тоже нужно залогиниться на localhost:8080/login, а затем можно пользоваться методами на localhost:8080/swagger-ui/index.html#/

