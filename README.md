# WarsztatyJava2 - Szkoła programowania
Obiektowa, bazodanowa warstwa aplikacji dla szkoły programowania.

---
## Funkcjonalności
Dla każdej z niżej wymienionych klas możliwe są następujące operacje:
- wczytywanie wszystkich danych
- wczytywanie danej na podstawie podanego id
- kasowanie danych
- zapisywanie nowych danych do bazy
####1. Użytkownicy
    Użytkownik jest identyfikowany po emailu.
####2. Grupy
    Użytkownik przynależy tylko do jednej grupy. Grupa może mieć wielu użytkowników.
####3. Zadanie
    Zadanie do rozwiązania – może mieć więcej niż jedno rozwiązanie.
####4. Rozwiązanie zadania
    Musi zawierać informacje:
    - jakiego zadania dotyczy,
    - kiedy zostało dodane,
    - który użytkownik dodał rozwiązanie.
---    
## Dodatkowe funkcjonalności:
- pobieranie wszystkich rozwiązań danego użytkownika
- pobieranie wszystkich rozwiązań danego zadania, 
  posortowanych od najnowszego do najstarszego
- pobieranie wszystkich członków danej grupy
---    
## Weryfikacja działania
#####Program 1 – zarządzanie użytkownikami
Program po uruchomieniu wyświetla na konsoli listę wszystkich użytkowników. 
Następnie wyświetla:

    Wybierz jedną z opcji:
    add – dodanie użytkownika,
    edit – edycja użytkownika,
    delete – usunięcie użytkownika,
    quit – zakończenie programu.

Po wykonaniu dowolnej z opcji, program ponownie wyświetla 
listę danych i zadaje pytanie o wybór opcji

#####Program 2 – zarządzanie zadaniami
Program po uruchomieniu wyświetla na konsoli listę wszystkich zadań. 
Następnie wyświetla:

    Wybierz jedną z opcji:
    add – dodanie zadania,
    edit – edycja zadania,
    delete – edycja zadania,
    quit – zakończenie programu.

Po wykonaniu dowolnej z opcji, program ponownie wyświetla
listę danych i zadaje pytanie o wybór opcji.

#####Program 3 – zarządzanie grupami
Program po uruchomieniu wyświetla na konsoli listę wszystkich grup. 
Następnie wyświetla:

    Wybierz jedną z opcji:
    add – dodanie grupy,
    edit – edycja grupy,
    delete – edycja grupy,
    quit – zakończenie programu

#####Program 4 – przypisywanie zadań
Program po uruchomieniu wyświetla w konsoli napis:

    Wybierz jedną z opcji:
    add – przypisywanie zadań do użytkowników,
    view – przeglądanie rozwiązań danego użytkownika,
    quit – zakończenie programu.

Po wykonaniu dowolnej z opcji, program ponownie zadaje pytanie o wybór opcji.

