import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProjectTable extends JFrame {
  private static final long serialVersionUID = 1L;
  private JTable table;

  public ProjectTable() {
    setTitle("Project Table");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    String[] columns = {"Project ID", "Project Name", "Project Description", "Project Status"};
    DefaultTableModel model = new DefaultTableModel(columns, 0);
    table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);

    try {
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");
      Statement stmt = conn.createStatement();
      String createTable = "CREATE TABLE IF NOT EXISTS PROJECT "
          + "(project_id INT AUTO_INCREMENT PRIMARY KEY,"
          + "project_name VARCHAR(255) NOT NULL,"
          + "project_description VARCHAR(255),"
          + "project_status VARCHAR(50))";
      stmt.executeUpdate(createTable);

      String insertQuery = "INSERT INTO PROJECT(project_name, project_description, project_status) VALUES "
          + "('Project A', 'Description A', 'In Progress'), "
          + "('Project B', 'Description B', 'On Hold'), "
          + "('Project C', 'Description C', 'Completed')";
      stmt.executeUpdate(insertQuery);

      String selectQuery = "SELECT * FROM PROJECT";
      ResultSet rs = stmt.executeQuery(selectQuery);

      while (rs.next()) {
        int projectId = rs.getInt("project_id");
        String projectName = rs.getString("project_name");
        String projectDesc = rs.getString("project_description");
        String projectStatus = rs.getString("project_status");

        Object[] row = {projectId, projectName, projectDesc, projectStatus};
        model.addRow(row);
      }

      conn.close();
    } catch (SQLException e) {
      System.out.println("SQL Exception: " + e.getMessage());
    }

    add(scrollPane);
    setVisible(true);
  }

  public static void main(String[] args) {
    new ProjectTable();
  }
}
