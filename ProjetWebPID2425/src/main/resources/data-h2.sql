MERGE into TGARNITURE(CODE,NOM,DISPONIBLE) values 
('SA','Salade',true),
('TO','Tomates',false),
('OE','Oeufs',true),
('MA','Maïs',true),
('CA','Carottes',false);

Merge into TARTICLE(CODE,NOM,DISPONIBLE) values
('BOUL','Boulette',true),
('POUL','Poulet Curry',true),
('JBFR','Jambon Fromage',true),
('FROM','Fromage',true);

MERGE into TSANDWICHES(CODE,PRIX) values 
('BOUL',3.5),
('POUL',3.5),
('JBFR',3.2),
('FROM',3.0);

MERGE into TUSER(username,email,nom,prenom,solde) values
('dvo','vo@isfce.be','VO','Didier',10.0),
('et1','et1@isfce.be','Nom Et1','Prénom Et1',5.3);
