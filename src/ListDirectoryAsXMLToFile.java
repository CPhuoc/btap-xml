import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ListDirectoryAsXMLToFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Nhập đường dẫn từ người dùng
        System.out.print("Nhập đường dẫn của thư mục bạn muốn liệt kê: ");
        String path = scanner.nextLine();
        // Tạo đối tượng File từ đường dẫn được cung cấp
        File directory = new File(path);
        // Kiểm tra xem đường dẫn có tồn tại không
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Đường dẫn không tồn tại hoặc không phải là một thư mục.");
            return;
        }
        // Tạo một đối tượng File để lưu kết quả vào tệp tin
        String xmlFilePath = "directory_structure.xml";
        File xmlFile = new File(xmlFilePath);
        // Tạo một đối tượng FileWriter để ghi vào tệp tin XML
        try (FileWriter writer = new FileWriter(xmlFile)) {
            // Ghi dấu mở đầu của tệp tin XML
            writer.write("<directory>" + System.lineSeparator());
            // Gọi phương thức đệ quy để liệt kê thư mục và tệp tin
            listFilesInDirectory(directory, writer, 1);
            // Ghi dấu kết thúc của tệp tin XML
            writer.write("</directory>");
            System.out.println("Liệt kê cây thư mục đã được lưu vào tệp tin " + xmlFilePath);
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi vào tệp tin.");
            e.printStackTrace();
        }
    }
    private static void listFilesInDirectory(File directory, FileWriter writer, int depth) throws IOException {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Nếu là thư mục, ghi thẻ mở và đệ quy vào thư mục con
                    writer.write(getIndent(depth) + "<" + file.getName() + ">" + System.lineSeparator());
                    listFilesInDirectory(file, writer, depth + 1);
                    writer.write(getIndent(depth) + "</" + file.getName() + ">" + System.lineSeparator());
                } else {
                    // Nếu là tệp, ghi thẻ tệp
                    writer.write(getIndent(depth) + "<file>" + file.getName() + "</file>" + System.lineSeparator());
                }
            }
        }
    }
    private static String getIndent(int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("    "); // 4 spaces for each depth level
        }
        return indent.toString();
    }
}