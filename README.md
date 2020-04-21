# projekt na przedmiot JPWP, Teleinformatyka II rok
Aplikacja pogodowa - współpraca z api jako problem programistyczny

# Zrobione:
*Karol:*
- repozytorium git
- ekran główny (MainActivity.class + layout.xml)
- splash screen (StartupActivity.class + layout.xml)
- fragmenty (FragmentX.class + layout.xml), podstawowa konfiguracja tj. działa, ale z funkcjonalnością słabo
- *fragment update:* nowy layout homeFragment, działa dodawanie do ulubionych *yay*
- *fragment update:* usuwanie z ulubionych wersja 0.3 - działa, ale na 95% trzeba będzie przejść na własny ArrayAdapter, 
                     żeby zaznaczać innym kolorem wybrane lokalizacje itp.
- *fragment update:* własny ArrayAdapter, zaznaczone lokalizacje mają inny kolor tła, zaznaczanie odbywa się przez naciśnięcie i                              przytrzymanie, zwykłe naciśnięcie przenosi do homeFragment i ustawia lokalizację na tą klikniętą w                                      favouritesFragment, zaznaczone lokaizacje można usunąć lub odznaczyć ponownym naciśnięciem i przytrzymaniem
- *fragment update:* settingsFragment ma teraz dodany tekst "about" oraz prosty przełącznik pozwalający włączyć i wyłączyć korzystanie z                      lokalizacji (na nie działa)
- *fragment update:* po krótkim naciśnięciu danego ulubionego miasta aplikacja automatycznie wyświetla homeFragment z wybraną                                lokalizacją
- *fragment update:* można dodawać i usuwać z ulubionych z poziomu homeFragment, robi to customowy checkbox, wrzucenie zmian jego stanu                      do osobnego wątka
- *layout update:* zmiejszenie rozmiaru czcionki nazwy miasta, teraz wszystko się mieści (a przynajmniej Pietropawłowsk Kamczacki) oraz                    wygląda lepiej
- propozycje kolorów
- logo
- style.xml, gradient.xml, attr.xml, nav_bar_bottom.xml, strings.xml
- powiązanie kolorów z attr (możliwość zrobienia motywów)
- ukrywanie navigation bar, action bar, przeźroczysty status bar
- nowy pasek nawigacyjny + ikony z wzięte z androida
- gradientowe tło
- animacje, ale w sumie to nie, bo nie działa tak jak powinno ¯\_(ツ)_/¯
- naprawa błędów
- rozwijanie konceptu i pozytywne podejście, praca w zespole
- pasek navigacyjny jest teraz przeźroczysty
- ładne komentowanie do późniejszej dokumentacji
- dodanie komunikacji między favourites fragmnet i main activity i na odwrót
- *komunikacja update:* home fragment może się komunikować z favourites fragment i na odwrót, main activity jest pośrednikiem
- obsługa obracania urządzenia: dane nie są tracone oraz wyświetlany jest odpowiedni fragment (ten sam co przed obróceniem)

*Sebastian:*
- pomysł na aplikację
- naprawa błędów
- rozwijanie konceptu i pozytywne podejście, praca w zespole
- stworzenie modelu POJO dla json
- rejestracja API
- implementacja biblioteki retrofit2, gson
- wyświetlanie odpowiadającej aktualnej pogodzie ikonki z API, za pomocą biblioteki Picasso
- lokalizacja urzadzenia za pomocą GPS lub internetu w celu wyświetlenia aktualnej i przewidywanej informacji pogodowych z danego miejsca 
- wyświetlenie stosowengo komunikatu, jeśli wybrane miasto nie istnieje
- upewnienie się, że wszystkie potrzebne funkcjonalności (GPS, internet) są włączone, a jesli nie, to wyświtlenie stosownego komunikatu 
- wyświetlanie bierzącej i przewidywanej informacji pogodowej dla wybranego przez użytkownika miasta 
- wyświetlanie bierzącej i przewidywanej informacji pogodowej dla wybranego z listy ulubionych miasta  
- przełączanie sie pomiedzy wyswietlaniem pogody na podstawie: położenia telefonu, wpisanej nazwy miasta, miasta zapisanego w ulubionych
- komunikacja między fragmentami za pośrednictwem interfejsów
- lokalizacja urządzenia przy pomocy Fused Location API.
- wyłączanie lokalizacji gdy wyświetlany jest inny ekran niż home
- wyświetlanie prośby o włączenie WiFi lub mobile data gdy przy starcie aplikacji wykryjemy, że nie mamy dostępu do internetu

# TODO:
- fragmenty: ~~komunikacja~~, onCreate() *chyba się obejdzie???*, ~~onViewCreated()~~, onPause() *chyba się obejdzie???*, ~~layout~~
- ~~pasek nawigacyjny~~
- ~~współpraca z api -> work in progress KS~~
- ~~przełączanie się między ręcznym wyszukiwaniem miasta i sczytywaniem danych z GPS/internetu -> work in progress KS~~
- ~~wyszukiwanie pogody w mieście dodanym do ulubionych -> work in progress KS~~
- ???
- deploy???
- ~~trzeba rozszerzyć pole z nazwą miast, bo dłuższe nazwy się nie mieszczą~~
- zrobić takie ładne komentarze jak Karol - KS
- zapisywanie danych w pamięci urządzenia
- "pull to refresh"
- widget
- powiadomienia
- inne inkonki
- auto-backup w manifeście, bo się pulta
- uporządkowanie kodu - zmienne w string.xml, przeniesienie części rzeczy z onCreateView()


# Napotkane problemy:
- komunikacja między fragmentami
- dodawanie i usuwanie z ulubionych
- po wybraniu miasta z ulubionych switch z lokalizacją nie wyłącza się pomimo tego, że odpowiednia informacja trafia do fragmentu "search"
