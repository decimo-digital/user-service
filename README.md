# User Service

Servizio che mantiene ed espone le informazioni degli utenti registrati

# ENV

E' possibile definire alcune variabili d'ambiente:

|    Nome     | Descrizione                                        | Obbligatorio |   Default   |
| :---------: | :------------------------------------------------- | :----------: | :---------: |
|    PORT     | Specifica la porta sulla quale il servizio ascolta |              |    8080     |
|   DB_NAME   | Il nome del database da utilizzare                 |      x       |             |
|   DB_TYPE   | Il tipo di connettore jdbc da utilizzare           |              | postgresql  |
|   DB_HOST   | L'url del database                                 |      x       |             |
|   DB_PORT   | La porta del DB                                    |              |    5432     |
| DB_USERNAME | L'utente da utilizzare per accedere al DB          |              |    admin    |
| DB_PASSWORD | La password per accedere al DB                     |              | ceposto2021 |

# Build

Per creare il `jar` eseguibile bisogna eseguire

```shell
./mvnw package
```

Opzionalmente si può aggiungere il flag `-DskipTests` per efitare l'avvio degli Unit/Integration test

# Eseguire il servizio in locale

Per avviare il servizio in locale bisogna specificare tutte le variabili d'ambiente [elencate prima](#ENV) o durante il
comando d'avvio (tipo `java -jar app.jar -DDB_NAME=<db_name> -DDB_HOST=<db_host>`) oppure impostandole come variabili
d'ambiente a livello di sistema con

```shell
export DB_NAME=''
export DB_HOST='';
java -jar app.jar
```