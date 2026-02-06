# Navigointi
Jetpack Composessa navigointi tarkoittaa siirtymistä Composable-ruutujen välillä yhden aktiviteetin sisällä.
NavController hoitaa siirtymät ja NavHost määrittelee, mitkä ruudut kuuluvat navigaatioon.

Sovelluksessa on kaksi ruutua, Home ja Calendar, joiden välillä voi siirtyä molempiin suuntiin.

# Arkkitehtuuri
Sovellus käyttää MVVM-arkkitehtuuria.
HomeScreen ja CalendarScreen jakavat saman ViewModelin, joka sisältää tehtävälistan ja liiketoimintalogiikan.

ViewModelin tila on jaettu molemmille ruuduille, joten muutokset näkyvät heti molemmissa näkymissä.

# CalendarScreen
CalendarScreen näyttää tehtävät päivämäärän mukaan ryhmiteltyinä, jolloin ne muodostavat kalenterimaisen näkymän.

# Tehtävien lisääminen ja muokkaus
Tehtävien lisääminen ja muokkaus tehdään AlertDialogilla:

addTask avaa tyhjän dialogin

editTask avaa dialogin esitäytetyillä tiedoilla

Dialogi päivittää ViewModelin tilan käyttäjän vahvistuksella.
