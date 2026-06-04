# Bataille

Projet Java pour un jeu de bataille utilisant Maven.

Lors d'une égalité , le problème est qu'il faut recréer un contexte de jeu (joueurs et pile de carte) à part 
et que ce contexte peut provoquer une autre égalité , j'ai pensé dans un premier temps à faire une fonction récursive puisque l'égalité peut appeler une égalité
mais la fonction a deux paramètres à gérer , donc j'ai utilisé les streams et la méthode removeIf (A partir de Java 21)

## 📋 Prérequis

- **Java 21** ou supérieur
- **Maven 3.6+** ([Télécharger Maven](https://maven.apache.org/download.cgi))

## 🚀 Commandes Maven

### Compiler le projet

```bash
mvn clean compile
```

Compile le code source en supprimant d'abord les fichiers générés précédemment.

### Exécuter les tests

```bash
mvn test
```

Exécute tous les tests unitaires du projet.

### Construire le projet

```bash
mvn clean package
```

Compile, teste et crée un fichier JAR dans le dossier `target/`.

### Générer un JAR exécutable

```bash
mvn clean package -DskipTests
```

Crée le JAR sans exécuter les tests.

### Nettoyer le projet

```bash
mvn clean
```

Supprime le dossier `target/` et tous les fichiers générés.

### Installer le projet localement

```bash
mvn install
```

Installe le JAR dans votre référentiel Maven local.

## ▶️ Lancer le projet

### Exécuter directement avec Maven

```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

Lance l'application directement sans créer de JAR.

### Exécuter après compilation

```bash
mvn clean compile
java -cp target/classes org.example.Main
```

Compile et exécute la classe principale.

### Exécuter le JAR généré

```bash
mvn clean package -DskipTests
java -jar target/Bataille-1.0-SNAPSHOT.jar
```

Construit le JAR et l'exécute.

## 📦 Structure du projet

```
Bataille/
├── src/
│   ├── main/
│   │   └── java/org/example/
│   │       ├── Main.java         # Classe principale
│   │       └── model/            # Classes métier
│   └── test/
│       └── java/                 # Tests unitaires
├── target/                       # Fichiers générés (après compilation)
└── pom.xml                       # Configuration Maven
```

## 🔧 Configuration Maven

Le projet est configuré avec :
- **Java 21** comme version source et cible
- **Encodage UTF-8** pour les fichiers
- **GroupId** : org.example
- **ArtifactId** : Bataille
- **Version** : 1.0-SNAPSHOT

## 📝 Notes

- Assurez-vous que JAVA_HOME est correctement configuré
- Pour plus d'informations sur Maven, consultez la [documentation officielle](https://maven.apache.org/)
- Pour utiliser `mvn exec:java`, vous devez ajouter le plugin maven-exec-plugin au pom.xml

## 👤 Auteur

Zekrom
