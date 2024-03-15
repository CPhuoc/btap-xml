import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class StudentListToXML {
    public static void main(String[] args) {
        // Tạo một ArrayList để lưu danh sách sinh viên
        ArrayList<Student> studentList = new ArrayList<>();
        // Nhập thông tin sinh viên từ người dùng
        inputStudentInfo(studentList);
        // Tạo nội dung XML từ danh sách sinh viên
        String xmlContent = convertToXML(studentList);
        // Lưu nội dung vào tệp tin XML trên thư mục cụ thể
        String directoryPath = "student_xml_files";
        String fileName = "student_list.xml";
        saveToFile(directoryPath, fileName, xmlContent);

        System.out.println("Danh sách sinh viên đã được lưu thành công vào thư mục " + directoryPath);
    }
    // Phương thức để nhập thông tin sinh viên từ người dùng
    private static void inputStudentInfo(ArrayList<Student> studentList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số lượng sinh viên: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự '\n' từ lệnh trước
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Nhập thông tin cho sinh viên thứ " + (i + 1) + ":");
            System.out.print("Tên: ");
            String name = scanner.nextLine();
            System.out.print("Tuổi: ");
            int age = scanner.nextInt();
            System.out.print("GPA: ");
            double gpa = scanner.nextDouble();
            scanner.nextLine(); // Đọc ký tự '\n' từ lệnh trước
            // Tạo đối tượng Sinh viên và thêm vào danh sách
            studentList.add(new Student(name, age, gpa));
        }
    }
    // Phương thức để chuyển đổi danh sách sinh viên thành XML
    private static String convertToXML(ArrayList<Student> studentList) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<students>").append(System.lineSeparator());
        for (Student student : studentList) {
            xmlBuilder.append("    <student>").append(System.lineSeparator());
            xmlBuilder.append("        <name>").append(student.getName()).append("</name>").append(System.lineSeparator());
            xmlBuilder.append("        <age>").append(student.getAge()).append("</age>").append(System.lineSeparator());
            xmlBuilder.append("        <gpa>").append(student.getGpa()).append("</gpa>").append(System.lineSeparator());
            xmlBuilder.append("    </student>").append(System.lineSeparator());
        }
        xmlBuilder.append("</students>");
        return xmlBuilder.toString();
    }

    // Phương thức để lưu nội dung vào tệp tin trên thư mục cụ thể
    private static void saveToFile(String directoryPath, String fileName, String content) {
        File directory = new File(directoryPath);
        // Tạo thư mục nếu chưa tồn tại
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // Tạo đường dẫn đến tệp tin
        String filePath = directoryPath + File.separator + fileName;
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi lưu vào tệp tin.");
            e.printStackTrace();
        }
    }
}
// Lớp đại diện cho Sinh viên
class Student {
    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }
}