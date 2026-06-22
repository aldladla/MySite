# MySite

Aplikacja webowa w Spring Boot, która działa jako prosta strona portfolio z logowaniem,
rejestracją użytkowników oraz podstronami: strona główna, projekty, o mnie i kontakt.

Projekt powstał jako moja własna strona do prezentacji umiejętności, projektów oraz
podstawowej pracy z backendem, bazą danych i widokami HTML.

## Podgląd

<!-- Zrób zrzuty ekranu, wrzuć je do folderu screenshots/ w repo i podmień ścieżki niżej -->
![Strona logowania](screenshots/login.png)
![Projekty – wykresy krypto i NASA APOD](screenshots/projekty.png)

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

2. Utwórz lokalną bazę danych MySQL:
   ```sql
   CREATE DATABASE bazadostronytest;
   ```
   Tabele utworzą się automatycznie przy pierwszym uruchomieniu (`ddl-auto=update`).

3. Ustaw zmienne środowiskowe.
   W IntelliJ: **Run → Edit Configurations → Environment variables**:
   ```
   DB_USERNAME=twoja_nazwa
   DB_PASSWORD=twoje_haslo
   NASA_API_KEY=twoj_klucz
   ```
   * `NASA_API_KEY` – darmowy klucz do pobrania z https://api.nasa.gov.
     Bez niego aplikacja użyje publicznego `DEMO_KEY` (z niskim limitem zapytań).

4. Uruchom aplikację:
   ```bash
   ./mvnw spring-boot:run      # Linux / macOS
   mvnw.cmd spring-boot:run    # Windows
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
* bezpieczne przechowywanie konfiguracji poza kodem (zmienne środowiskowe),
* organizację prostego projektu fullstackowego.

## Możliwe usprawnienia

W przyszłości chciałbym dodać:

* lepszą walidację formularzy,
* komunikaty błędów bezpośrednio w widokach,
* włączenie ochrony CSRF dla formularzy,
* Docker Compose z bazą MySQL,
* testy dla rejestracji i logowania,
* responsywny wygląd strony,
* deployment aplikacji online.

## Autor

**Aleksander Kostka** — Junior Backend Developer
olek.kostka03@gmail.com · [GitHub](https://github.com/aldladla) · [LinkedIn](#) <!-- wstaw link -->
