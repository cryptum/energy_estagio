use energysom;
select * from venda;
select * from funcionario;
select * from cliente;
insert into funcionario values(1,"nome","telefone","login","senha");
insert into venda values(1, 1, data, 100,"dinheiro");

INSERT INTO venda SET Id = 1,IdCliente = 1, IdFuncionario = 1, Datas = STR_TO_DATE( "31/05/2014", "%d/%m/%Y" ), TotalVenda = 100, FormaPagamento = "dinherio";

SELECT Id, DATE_FORMAT( Nascimento, "%d/%m/%Y" ) AS Nascimento FROM cliente;

use energysom;
select * from cliente;
insert into Cliente set id = 5, nome = "batata", cpf = "123", rg = "123", rua = "123", numero = "123", bairro = "123",
                cidade = "123", telefone = "123", celular1 = "123", celular2 = "123",
                 Nascimento = STR_TO_DATE( '12/12/1515', '%d/%m/%Y' ), observacao = "123";