-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: recarga
-- Source Schemata: recarga
-- Created: Fri Apr 17 08:14:54 2015
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;;

-- ----------------------------------------------------------------------------
-- Schema recarga
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `recarga` ;
CREATE SCHEMA IF NOT EXISTS `recarga` ;

-- ----------------------------------------------------------------------------
-- Table recarga.dddproduto
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `recarga`.`dddproduto` (
  `dddProduto` INT(11) NOT NULL ,
  `id` INT(2) NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id` (`id` ASC) ,
  UNIQUE INDEX `dddProduto_UNIQUE` (`dddProduto` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 403
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table recarga.erro
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `recarga`.`erro` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `codigo` INT(11) NULL DEFAULT NULL ,
  `data` DATETIME NULL DEFAULT NULL ,
  `mensagem` VARCHAR(128) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id` (`id` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 52
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table recarga.operadora
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `recarga`.`operadora` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `ativo` TINYINT(1) NOT NULL DEFAULT '1' ,
  `codigoOperadora` VARCHAR(255) NULL DEFAULT NULL ,
  `nomeOperadora` VARCHAR(255) NULL DEFAULT NULL ,
  `nomeRecargaOperadora` VARCHAR(255) NULL DEFAULT NULL ,
  `ultimaAtualizacaoOperadora` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id` (`id` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table recarga.operadora_produto
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `recarga`.`operadora_produto` (
  `operadora_id` BIGINT(20) NOT NULL ,
  `produtos_id` BIGINT(20) NOT NULL ,
  INDEX `FK12C07F07589E269C` (`produtos_id` ASC) ,
  INDEX `FK12C07F07A5FF829D` (`operadora_id` ASC) ,
  CONSTRAINT `FK12C07F07589E269C`
    FOREIGN KEY (`produtos_id` )
    REFERENCES `recarga`.`produto` (`id` ),
  CONSTRAINT `FK12C07F07A5FF829D`
    FOREIGN KEY (`operadora_id` )
    REFERENCES `recarga`.`operadora` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table recarga.produto
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `recarga`.`produto` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `PIN` VARCHAR(255) NULL DEFAULT NULL ,
  `ativo` TINYINT(1) NOT NULL ,
  `codigoProduto` VARCHAR(10) NOT NULL ,
  `modeloRecarga` VARCHAR(255) NULL DEFAULT NULL ,
  `msgHabilitacaoProduto` VARCHAR(255) NULL DEFAULT NULL ,
  `nomeProduto` VARCHAR(255) NULL DEFAULT NULL ,
  `precoCompraProduto` DOUBLE NOT NULL ,
  `precoVariavelProduto` DOUBLE NOT NULL ,
  `precoVendaProduto` DOUBLE NOT NULL ,
  `ultima_atualizacaoProduto` DATETIME NULL DEFAULT NULL ,
  `validadeProduto` INT(11) NOT NULL ,
  `valorIncrementoProduto` DOUBLE NOT NULL ,
  `valorMaximoProduto` DOUBLE NOT NULL ,
  `valorMinimoProduto` DOUBLE NOT NULL ,
  `operadoraId` BIGINT(20) NULL DEFAULT NULL ,
  `dataUpdate` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id` (`id` ASC) ,
  INDEX `FKED8DCEF9E71BCA78` (`operadoraId` ASC) ,
  CONSTRAINT `FKED8DCEF9E71BCA78`
    FOREIGN KEY (`operadoraId` )
    REFERENCES `recarga`.`operadora` (`id` ))
ENGINE = InnoDB
AUTO_INCREMENT = 394
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table recarga.permissao_usuario
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `recarga`.`permissao_usuario` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `role` VARCHAR(64) NOT NULL ,
  `usuario_id` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id` (`id` ASC) ,
  INDEX `FK16DAC762D3C6FBD` (`usuario_id` ASC) ,
  CONSTRAINT `FK16DAC762D3C6FBD`
    FOREIGN KEY (`usuario_id` )
    REFERENCES `recarga`.`usuario` (`id` ))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table recarga.usuario
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `recarga`.`usuario` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `data_cadastro` DATETIME NOT NULL ,
  `email` VARCHAR(128) NULL DEFAULT NULL ,
  `hash_senha` VARCHAR(128) NOT NULL ,
  `login` VARCHAR(64) NOT NULL ,
  `nome` VARCHAR(128) NOT NULL ,
  `ultimo_login` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id` (`id` ASC) ,
  UNIQUE INDEX `login` (`login` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table recarga.produto_dddproduto
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `recarga`.`produto_dddproduto` (
  `produto_id` BIGINT(20) NOT NULL ,
  `dddProduto_id` INT(11) NOT NULL ,
  INDEX `FKF4351B7BC81C39D7` (`dddProduto_id` ASC) ,
  INDEX `FKF4351B7BFE26D7DD` (`produto_id` ASC) ,
  INDEX `FK90FC839BC81C39D7` (`dddProduto_id` ASC) ,
  INDEX `FK90FC839BFE26D7DD` (`produto_id` ASC) ,
  CONSTRAINT `FK90FC839BC81C39D7`
    FOREIGN KEY (`dddProduto_id` )
    REFERENCES `recarga`.`dddproduto` (`id` ),
  CONSTRAINT `FK90FC839BFE26D7DD`
    FOREIGN KEY (`produto_id` )
    REFERENCES `recarga`.`produto` (`id` ),
  CONSTRAINT `FKF4351B7BC81C39D7`
    FOREIGN KEY (`dddProduto_id` )
    REFERENCES `recarga`.`dddproduto` (`id` ),
  CONSTRAINT `FKF4351B7BFE26D7DD`
    FOREIGN KEY (`produto_id` )
    REFERENCES `recarga`.`produto` (`id` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table recarga.recarga
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `recarga`.`recarga` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `codOnline` VARCHAR(255) NULL DEFAULT NULL ,
  `compra` VARCHAR(255) NULL DEFAULT NULL ,
  `dataDeSolicitacao` DATETIME NULL DEFAULT NULL ,
  `dataDeconfirmacao` DATETIME NULL DEFAULT NULL ,
  `ddd` VARCHAR(255) NULL DEFAULT NULL ,
  `fone` VARCHAR(255) NULL DEFAULT NULL ,
  `idTerminal` VARCHAR(255) NULL DEFAULT NULL ,
  `mensagem` VARCHAR(255) NULL DEFAULT NULL ,
  `pagamento` VARCHAR(255) NULL DEFAULT NULL ,
  `statusRecarga` INT(11) NULL DEFAULT NULL ,
  `usuarioLocal` VARCHAR(255) NULL DEFAULT NULL ,
  `valor` VARCHAR(255) NULL DEFAULT NULL ,
  `vencimento` VARCHAR(255) NULL DEFAULT NULL ,
  `produto_id` BIGINT(20) NOT NULL ,
  `idProdutoAjustado` VARCHAR(255) NULL DEFAULT NULL ,
  `nsu` VARCHAR(255) NULL DEFAULT NULL ,
  `statusRecargaServer` INT(11) NULL DEFAULT NULL ,
  `codigo` VARCHAR(255) NULL DEFAULT NULL ,
  `dataRV` VARCHAR(255) NULL DEFAULT NULL ,
  `face` VARCHAR(255) NULL DEFAULT NULL ,
  `lote` VARCHAR(255) NULL DEFAULT NULL ,
  `pago` VARCHAR(255) NULL DEFAULT NULL ,
  `pin` VARCHAR(255) NULL DEFAULT NULL ,
  `possuiBoleto` VARCHAR(255) NULL DEFAULT NULL ,
  `preco` VARCHAR(255) NULL DEFAULT NULL ,
  `reenvio` VARCHAR(255) NULL DEFAULT NULL ,
  `serie` VARCHAR(255) NULL DEFAULT NULL ,
  `uf_terminal` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id` (`id` ASC) ,
  INDEX `FK4080C15BFE26D7DD` (`produto_id` ASC) ,
  CONSTRAINT `FK4080C15BFE26D7DD`
    FOREIGN KEY (`produto_id` )
    REFERENCES `recarga`.`produto` (`id` ))
ENGINE = InnoDB
AUTO_INCREMENT = 4353364
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table recarga.suporte
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `recarga`.`suporte` (
  `loja` VARCHAR(50) NULL DEFAULT NULL ,
  `senha` VARCHAR(50) NULL DEFAULT NULL ,
  `nome` VARCHAR(50) NULL DEFAULT NULL ,
  `url` VARCHAR(100) NULL DEFAULT NULL )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
SET FOREIGN_KEY_CHECKS = 1;;
