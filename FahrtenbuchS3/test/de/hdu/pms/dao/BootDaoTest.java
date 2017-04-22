package de.hdu.pms.dao;

import java.util.List;

import org.junit.Test;

import de.hdu.pms.dao.BootDao;
import de.hdu.pms.model.Boot;

public class BootDaoTest extends AbstractDataAccessTest {
	private BootDao bootDao;
	private String tableName = "tbl_boot";

	public void setBootDao(BootDao bootDao) {
		this.bootDao = bootDao;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testFindById() {
		String[] tables = {tableName};
		// deleteFromTables is provided by the Superclass AbstractDataAccessTest
		// delete all rows from db table
		deleteFromTables(tables);
		// create new line in table
		bootDao.create("Uno", 1, "Einer");
		bootDao.create("Due", 2, "Zweier");
		List<Boot> alleBoote = bootDao.findAll();
		for (Boot p : alleBoote) {
			int id = p.getId();
			Boot found = bootDao.findById(id);
			assertEquals(p.getName(), found.getName());
		}
		// delete all rows from db table
		deleteFromTables(tables);
	}

	@Test
	public void testFindAll() {
		// already tested by testFindById()
		testFindById();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCreate() {
		String tabellennameBoot = "tbl_boot";
		String[] tables = {tabellennameBoot};
		deleteFromTables(tables);
		bootDao.create("Deggendorf", 4, "Vierer");
		int rows = countRowsInTable(tabellennameBoot);
		assertEquals("Die DB muss eine Zeile enthalten", 1, rows);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testFindFreie() {
		String[] tables = {tableName};
		// delete all rows from db table
		deleteFromTables(tables);
		// create new line in table
		bootDao.create("Uno", 1, "Einer");
		bootDao.create("Due", 2, "Zweier");
		List<Boot> alleBoote = bootDao.findFreie();
		for (Boot p : alleBoote) {
			int id = p.getId();
			Boot found = bootDao.findById(id);
			assertEquals(p.getName(), found.getName());
		}
		// delete all rows from db table
		deleteFromTables(tables);
	}

}
