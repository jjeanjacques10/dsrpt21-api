INSERT INTO ACAO (ID_ACAO, NOME, DESCRICAO, ATIVO ) VALUES (ACAO_SEQ.NEXTVAL, 'Ativar Armas', 'Ativa as armas', 1);
INSERT INTO ACAO (ID_ACAO, NOME, DESCRICAO, ATIVO ) VALUES (ACAO_SEQ.NEXTVAL, 'Ativar Pokemon', 'Ativa os pokemon', 1);
INSERT INTO ACAO (ID_ACAO, NOME, DESCRICAO, ATIVO ) VALUES (ACAO_SEQ.NEXTVAL, 'Ativar Maquinas', 'Ativando as m�quinas', 0);

INSERT INTO EXECUCAO (ID_EXECUCAO, ID_ACAO, DATA_EXECUCAO) VALUES (EXECUCAO_SEQ.NEXTVAL, 1, '2020-05-01 12:30:00');
INSERT INTO EXECUCAO (ID_EXECUCAO, ID_ACAO, DATA_EXECUCAO) VALUES (EXECUCAO_SEQ.NEXTVAL, 1, '2020-10-10 11:40:00');