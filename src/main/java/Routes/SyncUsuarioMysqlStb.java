package Routes;

import org.apache.camel.builder.RouteBuilder;

public class SyncUsuarioMysqlStb extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        //REALIZA CONSUMO DO TEMPO EM SEGUNDOS
        from("timer:myTimerUsuario?period=5000")
                .to("direct:SyncUsuarioMysqlStb");

        // REALIZAR SINCRONISMO DO REGISTRO ENTRE AS BASES QUE AINDA NÃO FORAM MIGRADOS
        from ("direct:SyncUsuarioMysqlStb")
                .setBody(constant("select * from usuario where ieMigracao = false"))
                .to("jdbc:mysql-db")
                .split(body())
                .transform(simple("insert into usuario (idUsuario, nmUsuario, dsEmail, nrCpf, ieMigracao) values (${body[idUsuario]},'${body[nmUsuario]}', '${body[dsEmail]}', '${body[nrCpf]}', true)"))
                .to("jdbc:mysql-stb-db")
                .to("direct:updateStatusMigracao")
                .end();

        //ATUALIZA OS REGISTROS DA ORIGEM COM FLAG QUE JÁ FOI MIGRADO
        from("direct:updateStatusMigracao")
                .setBody(constant("update usuario set ieMigracao = true where ieMigracao = false"))
                .to("jdbc:mysql-db")
                .end();

        //ROTA DE TEST
        from("direct:Tests")
                .setBody(constant("select * from usuario"))
                .to("jdbc:mysql-db")
                .end();

    }
}
