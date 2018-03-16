package gon.cue.jooq;

import gon.cue.cli.Options;

import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.Configuration;
import org.jooq.util.jaxb.Database;
import org.jooq.util.jaxb.Generator;
import org.jooq.util.jaxb.Jdbc;
import org.jooq.util.jaxb.Target;

public class GenerateFromDB {
    
    public static void startGenerate(Options opt){
        Configuration config = new Configuration()
				.withJdbc(
						new Jdbc().withDriver(opt.getDriver())
								.withUrl("jdbc:h2:file:./"+opt.getDBName()+";DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=TRUE;MVCC=TRUE")
								.withUser(
										opt.getUser())
								.withPassword(opt.getPassword()))
				.withGenerator(new Generator()
						.withDatabase(new Database().withName("org.jooq.util.h2.H2Database").withIncludes(".*")
								.withExcludes("").withInputSchema("PUBLIC"))
						.withTarget(new Target().withPackageName(opt.getPackage())
								.withDirectory(opt.getDestination())));

		try {
			GenerationTool.generate(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
