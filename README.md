# MySite

Aplikacja webowa w Spring Boot, która działa jako prosta strona portfolio z logowaniem, rejestracją użytkowników oraz podstronami: strona główna, projekty, o mnie i kontakt.

Projekt powstał jako moja własna strona do prezentacji umiejętności, projektów oraz podstawowej pracy z backendem, bazą danych i widokami HTML.

## Technologie

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Thymeleaf
* MySQL
* HTML
* CSS
* Maven

## Funkcje

Aplikacja umożliwia:

* rejestrację użytkownika,
* logowanie z użyciem Spring Security,
* zapis użytkowników w bazie MySQL,
* hashowanie haseł przy pomocy BCrypt,
* wyświetlanie podstron portfolio,
* pobieranie zdjęcia dnia z NASA APOD API,
* pobieranie przykładowych danych o cenach kryptowalut.

## Widoki aplikacji

* `/login` – logowanie i rejestracja
* `/main` – strona główna
* `/projekty` – projekty oraz dane z zewnętrznych API
* `/omnie` – informacje o mnie i moje CV
* `/kontakt` – strona kontaktowa

## Uruchomienie lokalnie

1. Sklonuj repozytorium:

```bash
git clone https://github.com/aldladla/MySite.git
cd MySite
```

2. Utwórz lokalną bazę danych MySQL, np.:

```sql
CREATE DATABASE bazadostronytest;
```

3. Ustaw dane do bazy w pliku `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bazadostronytest
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

4. Uruchom aplikację:

```bash
./mvnw spring-boot:run
```

Na Windowsie:

```bash
mvnw.cmd spring-boot:run
```

5. Wejdź w przeglądarce na:

```text
http://localhost:8080
```

## Czego się nauczyłem

W projekcie przećwiczyłem:

* tworzenie aplikacji webowej w Spring Boot,
* obsługę logowania i rejestracji użytkowników,
* konfigurację Spring Security,
* pracę z bazą danych przez Spring Data JPA,
* tworzenie widoków w Thymeleaf,
* łączenie backendu z zewnętrznymi API,
* organizację prostego projektu fullstackowego.

## Możliwe usprawnienia

W przyszłości chciałbym dodać:

* lepszą walidację formularzy,
* komunikaty błędów bezpośrednio w widokach,
* przeniesienie kluczy API i danych bazy do zmiennych środowiskowych,
* Docker Compose z bazą MySQL,
* testy dla rejestracji i logowania,
* responsywny wygląd strony,
* deployment aplikacji online.
