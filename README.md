# Introduction 
Afin d'appliquer les méthodologies et les notions enseignées dans le cours *Systèmes Multi-Agents, Agents Mobiles*, lors de notre 3ème année Génie Logiciel à l'ENSIAS, nous avons été chargé de réaliser une application de simulation Offre/Demande à l'aide du Framework Jade.

# Description du sujet
Il s’agit de développer une application de simulation de l’offre et de la demande en Jade. Cette
application multi-agents met en jeu :

- Un producteur (d'un produit) et 
- N consommateurs

Le **producteur** fixe un prix et le communique aux consommateurs.<br/>
Les **consommateurs** ont chacun sa propre demande concernant la quantité du produit qu’ils veulent acheter
à ce prix.

Ils le disent au producteur qui calcule ensuite son bénéfice en se basant sur la quantité totale vendue (q), le prix unitaire (p) et le coût unitaire de production (c) qu’on suppose indépendant de q :
`Benefice = (p-c)*q`

Pour simplifier on peut supposer que la demande est linéaire c’est-à-dire que :
`q = m*p`
où la pente `m = -Qmax/Pmax` avec 

- `Pmax` le prix maximum que le consommateur est prêt à payer
- `Qmax` la valeur de la « gourmandise » c’est-à-dire le montant que le
consommateur peut consommer même si le produit est gratuit.

![alt text][description]



# Méthodologie :
Nous présentons dans cette partie, la méthodologie suivie pour réaliser le projet :
## 1. Définition des données :
Les données échangées dans cet environnement sont :

- **Produit (Product) :** définit par son intitulé, coût unitaire de revient, et le prix de vente à l'unité.
- **Commande (Order) :** concerne un produit, avec une certaine quantité.

![alt text][beans]

## 2. Définition des Agents et leurs comportements :
### 2.1. Définition des Agents :
Les agents qui participent à cette activité sont :

- **Producteur (Producer)**: Cet agent est responsable de créer les agents consommateurs, de leur envoyer 
le prix et nom du produit, et de recevoir la commande de chaque consommateur. Possède une GUI.
- **Consommateur (Consumer)**: Crée par le consommateur, il calcule  la quantité à acheter en utilisant une **fonction de planification de la demande**, puis envoie sa commande au producteur. Il ne possède pas de GUI.


![alt text][actors]

### 2.2 Définition des Comportements :
- **Publication du Produit :** Par le **Producteur** auprès des consommateurs. Ce comportement est ajouté dynamiquement lorsque le producteur décide de publier un nouveau **Produit** à l'aide de son GUI. L'information est envoyé à la liste des consommateurs enregistrés chez le producteur.
- **Commande du produit :** Par un **Consommateur** après avoir reçu les informations concernant le produit. La quantitée à acheter est calculée à l'aide de la **fonction de planification de la demande**. Ensuite la **Commande** est envoyée au producteur. 
- **Réception d'une commande :** Par le **Producteur**, qui l'enregistre, puis met à jour le rapport des ventes : Quantitée et Bénéfice.

![alt text][behaviours]

<br/>
<br/>

## 3. Définition des messages
### 3.1. Performatifs FIPA et contenu
1.	Le **Producteur** envoie un `message ACL` avec le performatif **INFORM** contenant les détails du **Produit** (`instance de Product sérialisée`).
2.	Le **Consommateur** attend de recevoir un message ACL avec le performatif INFORM. Puis désérialise l'objet Product. 
3.	Il crée ensuite un `reply` avec le performatif **REQUEST** contenant les détails de la **Commande** (`instance de Order sérialisée`).
4.	Le **Producteur** attend de recevoir un message ACL avec le performatif **REQUEST**. Puis désérialise l'objet Order.

![alt text][communication]


# Description de la Solution proposée
## Structuration du code
![alt text][packages]
## Les Classes et leurs relations
A Continuer Diagramme de classe !!


[description]: DESCRIPTION.png
[beans]: DIAG_DONNEES.png
[actors]: DIAG_USECASE.png
[behaviours]: DIAG_SEQUENCE.png
[communication]: DIAG_COMM.png
[packages]: DIAG_PACKAGE.png