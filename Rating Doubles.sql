SELECT Ind_Rating, IndReview FROM mydb.Venture INNER JOIN mydb.IndRelationship INNER JOIN Individual WHERE Entrepeneur_idEntrepeneur = 1;

SELECT POW((`PPS Now`/`PPS Initial`), (1/(TIMESTAMPDIFF(DAY, '2020-12-31', EndDate)/-365))) - 1 AS AvgReturn, ExpectedReturn FROM mydb.Venture INNER JOIN EqVenture WHERE Entrepeneur_idEntrepeneur = 1;

SELECT CorpReview FROM CorpRelationship INNER JOIN Venture WHERE Entrepeneur_idEntrepeneur = 1;

SELECT sum(MonetaryValue) AS Interest FROM Venture INNER JOIN IndRelationship WHERE Entrepeneur_idEntrepeneur = 1;

SELECT PercentReturned FROM DebtVenture INNER JOIN Venture WHERE Entrepeneur_idEntrepeneur = 1;