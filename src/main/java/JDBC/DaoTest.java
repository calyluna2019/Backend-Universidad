package JDBC;

import java.sql.SQLException;

public class DaoTest {

    public static void main(String[] args) throws SQLException {

        ConnectionManager connectionManager = new ConnectionManager();

        EstudianteDao dao = new EstudianteDao(connectionManager);

        /*Estudiante e1 = new Estudiante();
        e1.setPadron("3345");
        e1.setApellido("luna");
        e1.setNombre("carlos");

        dao.grabar(e1);


        Estudiante e2 = new Estudiante();
        e2.setPadron("3456");
        e2.setApellido("nieva");
        e2.setNombre("alejandra");

        dao.grabar(e2);


        CarreraDao carreraDao = new CarreraDao(connectionManager);
        Carrera carrera = new Carrera();
        carrera.setNombre("Analista de Sistemas");

        carreraDao.grabar(carrera);
        */

        MateriaDao materiaDao = new MateriaDao(connectionManager);
        Materia materia = new Materia();
        materia.setNombre("Objeto 1");
        materia.setCargaHoraria(8);

        materiaDao.grabar(materia);



    }
}
