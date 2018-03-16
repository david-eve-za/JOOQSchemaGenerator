package gon.cue.jooq;

import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.Configuration;
import org.jooq.util.jaxb.Database;
import org.jooq.util.jaxb.Generator;
import org.jooq.util.jaxb.Jdbc;
import org.jooq.util.jaxb.Target;

public class GenerateFromDB {
    
    public static void startGenerate(){
        Configuration config = new Configuration()
				.withJdbc(
						new Jdbc().withDriver("org.h2.Driver")
								.withUrl("jdbc:h2:file:./LocalDB;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=TRUE;MVCC=TRUE")
								.withUser(
										"root")
								.withPassword("toor"))
				.withGenerator(new Generator()
						.withDatabase(new Database().withName("org.jooq.util.h2.H2Database").withIncludes(".*")
								.withExcludes("").withInputSchema("PUBLIC"))
						.withTarget(new Target().withPackageName("org.citi.model.ddl")
								.withDirectory("target/jooq")));

		try {
			GenerationTool.generate(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
