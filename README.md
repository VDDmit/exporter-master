# Exporter Application

Приложение для генерации CSV-отчётов по игрокам и командам.

---

## Быстрый старт

### 1. Клонируйте репозиторий

```bash
git clone https://github.com/VDDmit/exporter-master.git
cd exporter-master
```

---

### 2. Соберите проект

Убедитесь, что файл `target/exporter-0.0.1-SNAPSHOT.jar` существует. Если его нет, соберите проект вручную:

```bash
./mvnw clean package
```

Или, если у вас установлен Maven глобально:

```bash
mvn clean package
```

---

### 3. Запуск с Docker Compose

```bash
docker-compose up --build
```

Это:
- Соберёт Docker-образ
- Запустит контейнер приложения
- Откроет порт `8080` на хосте

---

### 4. Откройте приложение в браузере

Перейдите по ссылке:

http://localhost:8080/report

Вы увидите страницу со ссылками для скачивания CSV-отчётов по игрокам и командам.

---

### Остановка приложения

Для остановки и удаления контейнера выполните:

```bash
docker-compose down
```

---

## Примечания

- Убедитесь, что установлен [Docker](https://docs.docker.com/get-docker/)
- JAR-файл должен находиться по пути: `target/exporter-0.0.1-SNAPSHOT.jar`
