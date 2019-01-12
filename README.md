#Projet Systèmes Multi-Agents, Agents Mobiles :
---------------------------------------------
Réalisé par :

	Younes KASRI, Badr Eddine BAHOUM, 
	3ème année Génie Logiciel, 
	ENSIAS Promo 2019

A l'intention de :

	Prof. A EL FAKER, UM5, ENSIAS

Mot clés :

	Java, JADE, agent, distributed systems	

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
## Architecture applicative
![alt text][architecture]

Une plateforme regroupe un ensemble de conteneurs actifs d'agents.
Chaque conteneur est en fait une instance de la JRE.
Le conteneur principal contient des agents spéciaux. Il doit être toujours actif.
Les conteneurs contiennent des agents qui échangent des messages entre agents locaux, ou avec des agents distant à travers le réseau.

## Simultation de la demande
La consommateur détermine la quantitée à calculer à l'aide d'une fonction linéaire `q = m*p`
où la pente `m = -Qmax/Pmax`
C'est à l'Agent Consommateur de déterminer Qmax et Pmax :

- **Qmax :** La valeur de la gourmandise est calculée selon la règle suivante : Le consommateur se permet se permet d'utiliser son budget jusqu'à une valeur `MAX_BUDGET`. Qmax est alors calculée selon la relation suivante : `MAX_BUDGET*(1-RAND)/PrixProduit` avec `RAND` une variable aléatoire suivant une Loi Normale Réduite centrée sur 1/2 çàd que la valeur de la variable est `comprise entre 0 et 1`  
- **Pmax :** Le prix maximum que le consommateur est prêt à payer est calculé selon la règle : `prixProduit*(1+augmentation)` avec augmentation un nombre aléatoire `entre 0 et 50%`
 

# Implémentation de la solution
## Structuration du code
![alt text][packages]
## Les Classes et leurs relations
![alt text][classes]


# Résultats d'execution
## Test de l'execution :
1. Créer N agents consommateurs en appuyant N fois sur les bouton Create consumer Agent. 
2. Nous constatons que l'indicateur Number Of Consumers est incrémenté
2. Renseigner les informations du produit :
	- Nom du produit
	- Prix unitaire (de vente)
	- Coût unitaire (de revient)
3. Publier le produit chez les consommateurs en appuyant sur le bouton Advertise.
4. Nous pouvons alors voir les commandes des consommateurs affichées chacune dans un Popup.
5. Nous pouvons aussi constater que le rapport des ventes est mis à jour :
	- Quantitée totale vendue (du produit)
	- Bénéfice = (PU-CU)*Qté

![alt text][execution]

## Supervision de l'execution à l'aide de Jade GUI
### Jade Remote Agent Management GUI
![alt text][agents]

Nous remarquons que l'Agent Producteur crée les Agents Consommateurs sont crées dans le même conteneur où il réside.

### Jade Sniffer Agent
![alt text][sniffer]

En utilisant l'Agent Sniffer ont remarque que les messages sont échangés conformément à ce qui a été définit.


# Conclusion
Au terme de ce projet, nous sommes parvenus à réaliser un système multi-agents pour une simulation de l'Offre et la Demande. Nous avons effectué le monitoring de ce système à l'aide des outils offerts par Jade. Nous prévoyons d'améliorer notre système en y ajoutant la migration d'agents mobiles, et une simulation plus réaliste du Marché.


[description]: DESCRIPTION.png
[beans]: DIAG_DONNEES.png
[actors]: DIAG_USECASE.png
[behaviours]: DIAG_SEQUENCE.png
[communication]: DIAG_COMM.png
[architecture]: DIAG_DEPLOY.png
[packages]: DIAG_PACKAGE.png
[classes]: DIAG_CLASS.png
[execution]: EXECUTION.png
[agents]: JADE_RMA.png
[sniffer]: JADE_SNIFFER.png
