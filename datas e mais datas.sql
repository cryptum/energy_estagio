select * from venda;
select * from funcionario;
insert into funcionario values(1,"nome","telefone","login","senha");
insert into venda values(1, 1, data, 100,"dinheiro");

INSERT INTO venda SET Id = 1,IdCliente = 1, IdFuncionario = 1, Datas = STR_TO_DATE( "31/05/2014", "%d/%m/%Y" ), TotalVenda = 100, FormaPagamento = "dinherio";

SELECT Id, DATE_FORMAT( Datas, "%d/%m/%Y" ) AS Datas FROM venda;