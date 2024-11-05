MERGE into TGARNITURE(CODE,NOM,DISPONIBLE) values 
('SA','Salade',true),
('TO','Tomates',false),
('OE','Oeufs',true),
('MA','Maïs',true),
('CA','Carottes',false);

MERGE into TSANDWICHES(CODE,NOM,DISPONIBLE,PRIX) values 
('BOUL','Boulette',true,3.5),
('POUL','Poulet Curry',true,3.5),
('JBFR','Jambon Fromage',true,3.2),
('FROM','Fromage',true,3.0);
