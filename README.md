# Like Hero To Zero

## Beschreibung

Dieses Repository enthält die im Rahmen der Fallstudie des IU-Moduls **IPWA02-01 – Programmierung von industriellen Informationssystemen mit Java EE** entwickelte Java-EE-Webanwendung **Like Hero To Zero**.

Die Anwendung ermöglicht die Erfassung, Verwaltung und Veröffentlichung von CO₂-Emissionsdaten verschiedener Länder. Hierfür wurde ein rollenbasiertes Berechtigungskonzept mit den Benutzerrollen **Scientist** und **Editor** sowie einem Freigabeprozess implementiert.

---

## Verwendete Technologien

- Java
- Jakarta EE
- Jakarta Faces (JSF)
- Jakarta Persistence (JPA)
- Hibernate ORM
- Apache Tomcat 10
- Apache Maven
- MySQL (XAMPP)
- phpMyAdmin
- Eclipse IDE

---

## Voraussetzungen

Für die Ausführung der Anwendung werden folgende Komponenten benötigt:

- Java 11 oder höher
- Apache Tomcat 10
- Apache Maven
- XAMPP (MySQL und phpMyAdmin)
- Eclipse IDE (empfohlen)

---

## Einrichtung

1. Repository klonen.
2. XAMPP starten und den **MySQL-Dienst** aktivieren.
3. In phpMyAdmin eine leere Datenbank mit dem Namen **`likeherotozero`** erstellen.
4. Die Datenbankverbindung in

   ```
   src/main/resources/META-INF/persistence.xml
   ```

   bei Bedarf anpassen.

   Standardkonfiguration:

   - Datenbank: `likeherotozero`
   - Benutzer: `root`
   - Passwort: *(leer)*

5. Das Projekt in Eclipse importieren.
6. Das Maven-Projekt aktualisieren (**Maven → Update Project**).
7. Das Projekt auf einem Apache Tomcat 10 Server ausführen.
8. Die Anwendung im Browser starten.

> **Hinweis:** Vor dem Start der Anwendung muss der **MySQL-Dienst** in XAMPP ausgeführt werden. Die Datenbanktabellen werden beim ersten Start der Anwendung automatisch durch Hibernate erstellt (`hibernate.hbm2ddl.auto=update`).

---

## Testbenutzer

| Rolle | Benutzername | Passwort |

| Scientist | `scientist` | `1234` |

| Editor | `editor` | `1234` |

---


## Hinweise

Dieses Repository enthält den vollständigen Quellcode der entwickelten Anwendung einschließlich aller Ressourcen und Konfigurationsdateien.

Die Anwendung wurde im Rahmen der Fallstudie des IU-Moduls **IPWA02-01 – Programmierung von industriellen Informationssystemen mit Java EE** entwickelt.