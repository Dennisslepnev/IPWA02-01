# Like Hero To Zero

## Beschreibung

Dieses Repository enthält die im Rahmen der Fallstudie des IU-Moduls **IPWA02-01 – Programmierung von Webanwendungsoberflächen** entwickelte Jakarta-EE-Webanwendung **Like Hero To Zero**.

Die Anwendung ermöglicht die Erfassung, Verwaltung und Veröffentlichung von CO₂-Emissionsdaten. Hierfür wurde ein rollenbasiertes Berechtigungskonzept mit den Benutzerrollen **Scientist** und **Editor** implementiert.

---

## Verwendete Technologien

- Java
- Jakarta EE
- Jakarta Faces (JSF)
- Jakarta Persistence (JPA)
- Hibernate ORM
- Apache Tomcat
- Apache Maven
- XAMPP (MySQL und phpMyAdmin)
- Eclipse IDE

---

## Voraussetzungen

Für die Ausführung der Anwendung werden folgende Komponenten benötigt:

- Java
- Apache Tomcat
- Apache Maven
- XAMPP (inkl. MySQL und phpMyAdmin)

---

## Einrichtung

1. Repository klonen.
2. Apache Tomcat und XAMPP starten.
3. Den MySQL-Dienst innerhalb von XAMPP starten.
4. In phpMyAdmin eine leere Datenbank mit dem Namen **`likeherotozero`** erstellen.
5. Die Datenbankverbindung in

   ```
   src/main/resources/META-INF/persistence.xml
   ```

   bei Bedarf anpassen.

   Standardkonfiguration:

   - Datenbank: `likeherotozero`
   - Benutzer: `root`
   - Passwort: *(leer)*

6. Das Maven-Projekt aktualisieren.
7. Die Anwendung auf Apache Tomcat deployen.
8. Die Anwendung im Browser starten.

> **Hinweis:** Die Datenbanktabellen werden beim ersten Start der Anwendung automatisch durch Hibernate erstellt (`hibernate.hbm2ddl.auto=update`).

---

## Testbenutzer

| Rolle | Benutzername | Passwort |

| Scientist | scientist | 1234 |

| Editor | editor | 1234 |

---



## Hinweise

Dieses Projekt wurde im Rahmen der Fallstudie des IU-Moduls **IPWA02-01 – Programmierung von Webanwendungsoberflächen** entwickelt.

Das Repository enthält den vollständigen Quellcode der Anwendung einschließlich aller Ressourcen und Konfigurationsdateien.