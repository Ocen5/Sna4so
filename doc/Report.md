# 									***SNA4SO***

## ***Indice***

***[1. Introduzione](#1.-introduzione)***

***[2. Modello di dominio](#2.-modello-di-dominio)***

***[3. Requisiti Specifici](#3.-requisiti-specifici)***

***[4. System Design:](#4.-system-design)***

- ***[4.1. Stile architetturale adottato]( #4.1.-stile-architetturale-adottato)***
- ***[4.2. Diagramma dei package]( #4.2.-diagramma-dei-package)***
- ***[4.3. Diagramma dei componenti]( #4.2.-diagramma-dei-componenti)***
- ***[4.4. Commenti inerenti alle decisioni prese]( #4.4.-commenti-inerenti-alle-decisioni-prese)***

***[5. OO Design:](#5.-oo-design)***

- ***[5.1. Diagrammi delle classi e diagrammi di sequenza](#5.1.-diagrammi-delle-classi-e-diagrammi-di-sequenza)***

- ***[5.2. Menzionare l'eventuale applicazione di design pattern](#5.2.-menzionare-l'eventuale-applicazione-di-design-pattern)***

- ***[5.3. Commenti inerenti alle decisioni prese](#5.3.-commenti-inerenti-alle-decisioni-prese)***

***[6. Riepilogo del test](#6.-riepilogo-del-test)***

***[7. Manuale utente](#7.-manuale-utente)***

***[8. Processo di sviluppo e organizzazione del lavoro](#8.-processo-di-sviluppo-e-organizzazione-del-lavoo)***

***[9. Analisi retrospettiva](#9.-analisi-retrospettiva)***


## 1. Introduzione

**Stack Overflow** è un sito web che fa parte della rete Stack Exchange in cui si possono porre domande riguardo a vasti argomenti inerenti alla programmazione.

Il lavoro che ci è stato commissionato dal Professore Lanubile Filippo e dai suoi collaboratori, SNA4SO (acronimo di Social Network Analysis applicata a Stack Overflow), è un'applicazione a linea di comando (CLI). Il modello sul quale si basa tale applicazione è rappresentato da un grafo orientato, in cui i nodi rappresentano gli utenti mentre gli archi partono dall'utente che risponde a un utente che ha posto una domanda.

Per quanto riguarda i tools adottati per lo sviluppo, ci siamo serviti di:

- Java come linguaggio di programmazione;
- Eclipse come IDE;
- Visual Paradigm per la modellazione UML;
- Gradle per la gestione delle dipendenze e build automation;
- Git per il controllo di versione;
- JUnit per il test automatizzato;
- Jacoco e Coveralls per la copertura del test;
- Checkstyle e FindBugs per l'analisi statica del codice;
- Travis CI come servizio per creare e testare il progetto;
- Docker per il deployment di applicazioni all'interno di contenitori software;
- Slack come strumento di comunicazione.

Noi componenti del gruppo, cioè Aloia Marcello Giovanni, Antonacci Michele, Belvedere Vincenzo, Conticchio Giuseppe, Dattoli Alessandro,  Leone Marco e Lopes Michelangelo, abbiamo scelto il nome del gruppo da una lista nella quale vi erano i vincitori del Turing Award. Il nome che abbiamo scelto è ***codd***, nonché Edgar F. Codd che è stato un informatico britannico fondatore della teoria delle basi di dati relazionali.

## 2. Modello di dominio

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/diagrammi/modello-dominio.png)

## 3. Requisiti Specifici

Nello **Sprint 1** ci è stato chiesto come obiettivo di visualizzare i nodi in forma tabulare accedendo alla Google Cloud API in lettura e scrittura, fornendoci le seguenti User Story:

- ***User Story 1:***

*"Visualizzare la lista dei primi 100 id utente (User) che hanno fatto almeno una domanda (Question) in un dato anno, mese e giorno"*.

Criteri di accettazione

Eseguendo il comando:

*docker run --rm softeng1819infuniba/<nome_repository> yyyy=2016 mm=02 dd=11 type=question limit=100*

il risultato è una lista di owner_user_id univoci e non nulli, ordinati in modo crescente come da esempio in figura:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/userStory/UserStory1-Sprint-1.png)

- ***User Story 2:***

*"Visualizzare la lista dei primi 100 id utente (User) che hanno dato almeno una risposta (Answer) in un dato anno, mese e giorno".*

Criteri di accettazione

Eseguendo il comando:

*docker run --rm softeng1819infuniba/<nome_repository> yyyy=2016 mm=02 dd=11 type=answer limit=100*

il risultato è una lista di owner_user_id univoci e non nulli, ordinati in modo crescente come da esempio in figura:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/userStory/UserStory2-Sprint-1.png)

- ***User Story 3:***

*"Visualizzare la lista dei primi 100 id utente (User) che hanno fatto almeno un Post in un dato mese, anno, giorno (un Post può essere una domanda o una risposta)".*

Criteri di accettazione

Eseguendo il comando:

*docker run --rm softeng1819infuniba/<nome_repository> yyyy=2016 mm=02 dd=11 type=post limit=100*

il risultato è una lista di owner_user_id univoci e non nulli, ordinati in modo crescente come da esempio in figura:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/userStory/UserStory3-Sprint-1.png)

- ***User Story 4:***

*"Visualizzare la lista dei primi 100 id utente (User) che hanno fatto almeno una domanda (Question) su un dato argomento (Tag) in un dato mese e anno".*

Criteri di accettazione

Eseguendo il comando:

*docker run --rm softeng1819infuniba/<nome_repository> yyyy=2016 mm=02 type=question taglike=java limit=100*

il risultato è una lista di owner_user_id univoci e non nulli, ordinati in modo crescente come da esempio in figura:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/userStory/UserStory4-Sprint-1.png)

- ***User Story 5:***

*"Visualizzare la lista dei primi 100 id utente (User) che hanno dato almeno una risposta (Answer) su un dato argomento (Tag) in un dato mese e anno".*

Criteri di accettazione

Eseguendo il comando:

*docker run --rm softeng1819infuniba/<nome_repository> yyyy=2016 mm=02 type=answer taglike=java limit=100*

il risultato è una lista di owner_user_id univoci e non nulli, ordinati in modo crescente come da esempio in figura:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/userStory/UserStory5-Sprint-1.png)

- ***User Story 6:***

*"Visualizzare la lista dei primi 100 id utente (User) che hanno fatto almeno un Post su un dato argomento (Tag) in un dato mese e anno (un Post può essere una domanda o una risposta)".*

Criteri di accettazione

Eseguendo il comando:

*docker run --rm softeng1819infuniba/<nome_repository> yyyy=2016 mm=02 type=post taglike=java limit=100*

il risultato è una lista di owner_user_id univoci e non nulli, ordinati in modo crescente come da esempio in figura:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/userStory/UserStory6-Sprint-1.png)

Nello **Sprint 2** ci è stato chiesto di visualizzare gli archi diretti in forma tabulare (gli archi diretti partono dall'utente che risponde e arrivano all'utente che ha posto una domanda), fornendoci le seguenti User Story:

- ***User Story 1:***

*"Visualizzare la lista delle prime 100 coppie (from, to) relative a domande (Question) poste in un dato anno, mese e giorno"*.

Criteri di accettazione

Eseguendo il comando:

*docker run --rm softeng1819infuniba/<nome_repository> yyyy=2016 mm=02 dd=11 type=question edge=yes limit=100*

il risultato è una lista come da esempio in figura:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/userStory/UserStory1-Sprint-2.png)

-Verificare che per ogni domanda e risposta sia visualizzata una riga con la coppia (from, to) dove to è l'id dell'utente che ha posto la domanda e from è l'id dell'utente che ha risposto.
-Verificare che le coppie (from, to) non contengano valori nulli.
-Verificare che le coppie (from, to) siano ordinate in modo crescente.
-Verificare che le coppie (from, to) non siano ripetute.

- ***User Story 2:***

*"Visualizzare la lista delle prime 100 coppie (from, to) relative a domande (Question) poste da un determinato utente".*

Criteri di accettazione

Eseguendo il comando:

*docker run --rm softeng1819infuniba/<nome_repository> type=question user=1109 edge=yes limit=100*

il risultato è una lista come da esempio in figura:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/userStory/UserStory2-Sprint-2.png)

-Verificare che per ogni domanda e risposta sia visualizzata una riga con la coppia (from, to) dove to è l'id dell'utente che ha posto la domanda, specificato come valore di user, e from è l'id dell'utente che ha risposto.
-Verificare che le coppie (from, to) non contengano valori nulli.
-Verificare che le coppie (from, to) siano ordinate in modo crescente.
-Verificare che le coppie (from, to) non siano ripetute.

- ***User Story 3:***

*"Visualizzare la lista delle prime 100 coppie (from, to) relative a risposte (Answer) date da un determinato utente".*

Criteri di accettazione

Eseguendo il comando:

*docker run --rm softeng1819infuniba/<nome_repository> type=answer user=86 edge=yes limit=100*

il risultato è una lista come da esempio in figura:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/userStory/UserStory3-Sprint-2.png)

-Verificare che per ogni domanda e risposta sia visualizzata una riga con la coppia (from, to) dove to è l'id dell'utente che ha posto la domanda e from è l'id dell'utente che ha risposto, specificato come valore di user.
-Verificare che le coppie (from, to) non contengano valori nulli.
-Verificare che le coppie (from, to) siano ordinate in modo crescente.
-Verificare che le coppie (from, to) non siano ripetute.

- ***User Story 4:***

*"Visualizzare la lista delle prime 100 triple (from, to, weight) relative a domande (Question) poste in un dato anno, mese e giorno".*

Criteri di accettazione

Eseguendo il comando:

*docker run --rm softeng1819infuniba/<nome_repository> yyyy=2016 mm=02 dd=11 type=question edge=yes weight=yes limit=100*

il risultato è una lista come da esempio in figura:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/userStory/UserStory4-Sprint-2.png)

-Verificare che per ogni domanda e risposta sia visualizzata una riga con la tripla (from, to, weight) dove to è l'id dell'utente che ha posto la domanda, from è l'id dell'utente che ha risposto e weight è il peso associato al numero di volte che l'utente from ha risposto all'utente to.
-Verificare che le triple (from, to, weight) non contengano valori nulli.
-Verificare che le triple (from, to, weight) siano ordinate in modo crescente.
-Verificare che non ci siano triple con (from, to) ripetuti.

- ***User Story 5:***

*"Visualizzare la lista delle prime 100 triple (from, to, weight) relative a domande (Question) poste da un determinato utente".*

Criteri di accettazione

Eseguendo il comando:

*docker run --rm softeng1819infuniba/<nome_repository> type=question user=1109 edge=yes weight=yes limit=100*

il risultato è una lista come da esempio in figura:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/userStory/UserStory5-Sprint-2.png)

-Verificare che per ogni domanda e risposta sia visualizzata una riga con la tripla (from, to, weight) dove to è l'id dell'utente che ha posto la domanda, specificato come valore di user, from è l'id dell'utente che ha risposto e weight è il peso associato al numero di volte che l'utente from ha risposto all'utente to.
-Verificare che le triple (from, to, weight) non contengano valori nulli.
-Verificare che le triple (from, to, weight) siano ordinate in modo crescente.
-Verificare che non ci siano triple con (from, to) ripetuti.

- ***User Story 6:***

*"Visualizzare la lista delle prime 100 triple (from, to, weight) relative a risposte (Answer) date da un determinato utente".*

Criteri di accettazione

Eseguendo il comando:

*docker run --rm softeng1819infuniba/<nome_repository> type=answer user=86 edge=yes weight=yes limit=100*

il risultato è una lista come da esempio in figura:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/userStory/UserStory6-Sprint-2.png)

-Verificare che per ogni domanda e risposta sia visualizzata una riga con la tripla (from, to, weight) dove to è l'id dell'utente che ha posto la domanda, from è l'id dell'utente che ha risposto, specificato come valore di user, e weight è il peso associato al numero di volte che l'utente from ha risposto all'utente to.
-Verificare che le triple (from, to, weight) non contengano valori nulli.
-Verificare che le triple (from, to, weight) siano ordinate in modo crescente.
-Verificare che non ci siano triple con (from, to) ripetuti.

## 4. System Design

#### 4.1. Stile architetturale adottato

Lo stile architetturale adottato per la progettazione del software è il ***Pipe and Filter***. Secondo questo stile architetturale i sottosistemi elaborano i dati ricevuti in input e mandano il proprio output come input per gli altri sottosistemi.

#### 4.2. Diagramma dei package

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/diagrammi/diagramma-package.png)

#### 4.3. Diagramma dei componenti

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/diagrammi/diagramma-componenti.png)

Il primo servizio esterno che abbiamo utilizzato per interagire con il database messo a disposizione da Stack Overflow è l'API di ***Google BigQuery***, che permette un'analisi di grandi set di dati utilizzando come linguaggio l'SQL Standard. Il secondo servizio esterno è l'API di ***Google Spreadsheet*** che, attraverso un foglio di calcolo elettronico, permette di visualizzare il risultato della query (cioè l'interrogazione da parte di un utente al database). L'ultimo servizio esterno è l'API di ***Google Drive*** che, attraverso un indirizzo URL (fornito da Google Spreadsheet), consente di salvare lo spreadsheet sul Drive.

#### 4.4. Commenti inerenti alle decisioni prese

Lo stile architetturale adottato inizialmente è stata una scelta dovuta dall'analisi del ***main*** già fornitoci dal docente. Esso richiama un servizio (ISOQuery) che forniva un input per il successivo servizio (GoogleDocUtils).

Nello sprint 2 abbiamo deciso di identificare un servizio per l'analisi dei parametri in input da command line, un servizio per la scelta della query e i due servizi già esistenti a cui abbiamo aggiunto le nuove user stories.

Infine, nello sprint 3, abbiamo separato ulteriormente le responsabilità delle componenti distinguendo, in accordo con la Tassonomia ECB, le componenti per connettersi ai servizi forniti da Google Cloud Platform, le query idonee al database di Stack Overflow, implementato il servizio per l'analisi dei parametri in input dividendolo in più componenti, rispettando la suddetta tassonomia.

## 5. OO Design:

#### 5.1. Diagramma delle classi e diagramma di sequenza

*Diagramma delle classi per la user story 4 dello sprint 1*:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/diagrammi/userstory4-sp1-d-classi.png)

*Diagramma di sequenza per la user story 4 dello sprint 1*:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/diagrammi/userstory4-sp1-d-sequenza.png)

*Diagramma delle classi per la user story 6 dello sprint 2*:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/diagrammi/userstory6-sp2-d-classi.png)

*Diagramma di sequenza per la user story 6 dello sprint 2*:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/diagrammi/userstory6-sp2-d-sequenza.png)

#### 5.2. Menzionare l'eventuale applicazione di design pattern

Abbiamo deciso di utilizzare alcuni dei design pattern per ottenere buone soluzioni progettuali per risolvere problemi ricorrenti all'interno della nostra applicazione.

Il design pattern che abbiamo utilizzato per quanto riguarda quelli di tipo Creational è il ***Factory Method***, ***Decorator*** e ***Flyweight*** per quelli di tipo Structural e infine ***Interpreter*** per i Behavioral patterns.

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/diagrammi/diagramma-classi-sprint3.png)

#### 5.3. Commenti inerenti alle decisioni prese

Dopo aver appreso più nozioni inerenti all'OO design, abbiamo deciso di effettuare, a nostro parere,  il refactoring dell'intero progetto per apportare una maggiore estendibilità dell'intera applicazione. In questa fase abbiamo applicato alcuni dei design pattern illustratici durante il corso.

Abbiamo notato che nella nostra applicazione ci fosse la necessità di decidere a runtime quale classe istanziare, a seconda dei parametri inseriti da command line e a tal proposito abbiamo utilizzato il Factory Method.

Successivamente, avendo un gran numero di entità potenzialmente istanziabili abbiamo usato il Flyweight,  che ci ha permesso di supportare in maniera più efficiente la scelta della query. Inoltre, essendo alcune query composizione di altre, abbiamo fatto uso del Decorator estendendo le responsabilità delle query semplici facendole diventare composte. E' stato opportuno dividere ulteriormente le query a seconda della tipologia dei parametri in input che alcune di esse condividono.

Per quanto riguarda il controllo dei parametri in input da command line per la scelta della query abbiamo creato un package apposito, dovendo separare l'analisi della validità dei parametri in input da quella della scelta della query al fine di rendere facilmente estendibile l'inserimento di nuovi parametri in input e la creazione di una nuova query, anche successivamente. In particolare ci siamo avvalsi dell'Interpreter per definire una grammatica che rendesse più facile il controllo sulla validità degli input, potendo definire il dominio degli attributi.

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/diagrammi/diagramma-dp1.png)

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/diagrammi/diagramma-dp2.png)

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/diagrammi/diagramma-dp3.png)

## 6. Riepilogo del test

Conseguentemente al down di Google Cloud Platform i casi di test che necessitavano di tale piattaforma hanno riscontrato dei problemi, diminuendo considerevolmente la percentuale di Coveralls. Dopo che i server di Google Cloud Platform sono stati ripristinati, i risultati dei test sono i seguenti:

![](https://github.com/softeng1819-inf-uniba/progetto1819-codd/blob/master/doc/drawings/coveralls.png)

## 7. Manuale utente 

Per poter utilizzare la nostra applicazione, che verrà eseguita in un container sul computer locale, è necessario in primo luogo registrarsi a [**docker.com**](https://www.docker.com/). Dopo essersi registrati con successo bisogna effettuare il download del software Docker. Dopo aver eseguito correttamente il download e l'installazione di Docker occorre:

- avviare Docker localmente (una volta aperta l’applicazione, bisogna attendere che nel menu di Docker compaia la scritta “Docker is running”);

- se si utilizza Windows selezionare `Switch to Linux containers` nel menu di Docker;

- digitare nel terminale il seguente comando:

  ```
  docker pull softeng1819infuniba/<codd>
  ```

  (**softeng1819infuniba** è il nome della organization che ospita i repository).

- attendere che Docker scarichi l’immagine dell’applicazione e successivamente digitare il seguente comando:

  ```
  docker run --rm softeng1819infuniba/<codd>
  ```

- per conoscere le istruzioni valide di Docker, digitare il seguente comando:

  ```
  docker run --rm softeng1819infuniba/<codd> help
  ```

**N.B.:**

1. L’opzione `—-rm` serve per far sì che docker fermi l’esecuzione del container nel momento in cui l’applicazione eseguita al suo interno termina.
2. Si assume per default la versione *:latest* dell'immagine caricata su *docker.com* e quindi si omette di specificare esplicitamente il `<version_number>`.
3. Per i comandi completi utilizzabili vedere la sezione [Requisiti Specifici](#3.-requisiti-specifici).

## 8. Processo di sviluppo e organizzazione del lavoro

Il processo di sviluppo che è stato adottato è quello ***agile***. Questo processo di sviluppo del software è molto flessibile rispetto ai cambiamenti in corso d' opera, necessita di poca documentazione e lo stile utilizzato è di tipo iterativo, in cui ogni iterazione ha una durata breve e costante. Il modello adottato è basato sul framework agile Scrum, o per essere più precisi un simil-Scrum, concepito per uno sviluppo iterativo e incrementale. In questo metodo il processo di gestione viene diviso in sprint durante il quale vengono analizzate e sviluppate le esigenze di un committente/cliente. Inizialmente è stato definito il product backlog il quale definisce una lista di requisiti necessari per lo sviluppo del progetto. Successivamente è stato determinato lo sprint-backlog, il quale definiva una serie di user story (compiti) che devono essere eseguiti dai componenti del team nei singoli sprint. A ogni user story è stato assegnato uno story point (i cui valori assunti rispettano la sequenza di Fibonacci), calcolato in base all'effort (sforzo) necessario per poter svolgere la user story. Il progetto è stato suddiviso in quattro sprint della durata di 15 giorni circa l'uno. Al termine di ogni sprint si è svolto uno sprint review generale, per verificare se l'obiettivo prefissato è stato raggiunto valutando i risultati e per decidere eventualmente cosa migliorare e cosa tralasciare. Nel primo (sprint 0) ci siamo occupati della configurazione dei tools che abbiamo utilizzato, dovendo mostrare padronanza e familiarità con questi, in particolar modo con GitHub che è un servizio di hosting per progetti software. Nel secondo e terzo (sprint 1 e sprint 2)  abbiamo iniziato il lavoro di sviluppo della nostra applicazione, svolgendo le varie user story, rappresentanti le queries (interrogazioni), che ci sono state assegnate. Infine nell'ultimo (sprint 3) ci siamo occupati di effettuare un refactoring generale di testare l'applicazione sviluppata attraverso strumenti di analisi statica del codice, di copertura di codice, di test sul codice scritto e di scrivere la relazione tecnica.

L'organizzazione del lavoro che abbiamo adottato è stato sia il pair-programming sia il lavoro autonomo. I criteri per il lavoro in pair-programming che abbiamo adottato sono stati principalmente l'intesa che i componenti avevano prima dell'instaurazione di questo gruppo e la locazione geografica dei singoli componenti così da poterne favorire lo sviluppo. Alla fine della definizione di ogni singolo sprint, ogni user story e issue sono state assegnate a uno o a un massimo di due componenti. Per quanto riguarda invece la suddivisione dei compiti da svolgere, abbiamo cercato di rendere la distribuzione del lavoro in maniera più equa possibile. Ad esempio nello sprint 2, abbiamo assegnato a ogni componente del gruppo un totale di 5 story point.

Per rimanere in contatto abbiamo utilizzato diverse piattaforme di comunicazione come Whatsapp, Telegram, Discord e un workspace personale su Slack sulle quali ci siamo potuti confrontare sulle problematiche riscontrate e sulle possibili soluzioni, anche scambiandoci file e/o riferimenti a pagine web inerenti a quel determinato problema. Oltre all'utilizzo di queste piattaforme abbiamo deciso anche di incontrarci. Questa è stata una soluzione molto efficace in quanto abbiamo potuto risolvere con maggiore facilità alcune delle problematiche che abbiamo riscontrato durante lo sviluppo di questa applicazione.

## 9. Analisi retrospettiva

Gli aspetti positivi riscontrati dal gruppo durante la realizzazione di questo progetto sono state principalmente di tipo educativo. In particolar modo abbiamo trovato stimolante la possibilità di metterci alla prova in un ambiente di sviluppo collettivo, imparando a confrontarci con i diversi componenti sullo stesso progetto, utilizzando vari tools che ci saranno utili nel futuro della nostra carriera lavorativa. Una cosa riscontrata da tutti è stata la soddisfazione nell'aver ampliato le conoscenze in ambito di progettazione software inerente a un nuovo paradigma di programmazione con relativo linguaggio di programmazione che stavamo studiando in parallelo a un altro corso. Molto interessante è stato l'apprendimento dei modelli di design pattern e del metodo di sviluppo agile Scrum. Molto importante è stata la possibilità di scegliere i componenti del gruppo di sviluppo poiché avevamo già una buona intesa e questo progetto ne ha favorito la crescita. Infine ci ha fatto molto piacere sviluppare un'applicazione che è stata il frutto delle nostre conoscenze ampliate nel corso del semestre.

Come aspetti negativi sono stati riscontrati problemi relativi al dovere lavorare in contemporanea allo studio di altre materie, non potendo esprimere al meglio le proprie capacità e a volte le nostre idee. E' stato inoltre constatata la necessità di avere la possibilità di effettuare più build sul servizio offerto da Travis CI. Un aspetto che avrebbe potuto aiutarci sin dall'inizio è l'introduzione ai design pattern un po' prima così da poter arrivare all'ultimo sprint con maggiori conoscenze e le idee anche più chiare su come strutturare la nostra applicazione. A tal proposito abbiamo trovato sconveniente fare refactoring e test nello stesso sprint poiché ha causato un maggiore effort da parte di ogni singolo componente del gruppo, che ha indotto ad esempio una cattiva gestione delle eccezioni. Si sarebbe voluto più materiale di confronto e apprendimento oltre a una maggiore spiegazione sui tools utilizzati. Inoltre è stato constato che si sarebbe potuto suddividere il tempo suddiviso per ogni singolo sprint in maniera differente e più proporzionata al carico di lavoro da dover eseguire, ad esempio avere meno giorni per lo sprint 0 e di più per lo sprint 3. 

Tra gli aspetti che ci hanno fatto impazzire abbiamo constatato che il down del server di Google Cloud proprio nelle ore finali dello sprint 3 è stata una vera disgrazia. Inoltre si sarebbe gradita più informazione relativa alle classi che abbiamo utilizzato nel codice; alcune classi dell'api di Google sono state particolarmente difficili da comprendere. L'implementazione della classe SOQuerySelector è stata di non poco sforzo mentale del team. Lo scrivere, infine, la javadoc è stata considerata particolarmente dispendiosa in termini di effort.
