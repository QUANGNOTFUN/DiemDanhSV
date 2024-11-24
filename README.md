# Hệ thống quản lý điểm danh

**Hệ thống quản lý điểm danh** là một ứng dụng Java được phát triển để hỗ trợ các cơ sở giáo dục trong việc theo dõi và quản lý điểm danh. Ứng dụng này sử dụng **JavaFX** cho giao diện người dùng và **MySQL** để quản lý cơ sở dữ liệu, giúp giáo viên và sinh viên dễ dàng quản lý và theo dõi tình trạng điểm danh.

## Mô tả

Hệ thống cung cấp các tính năng như đăng nhập cho giáo vụ, quản lý môn học, sinh viên, và theo dõi điểm danh của sinh viên. Ứng dụng có thể được mở rộng thêm với các tính năng mới và cải thiện giao diện người dùng để mang lại trải nghiệm tốt hơn cho người sử dụng.

## Các chức năng chính

- **Đăng nhập và quản lý cho giáo vụ**
  - Đăng nhập bảo mật cho giáo vụ.
  - Tạo, chỉnh sửa và xóa thông tin môn học.
  - Thêm danh sách sinh viên vào môn học thông qua các phương thức:
    - Chọn từ danh sách sinh viên có sẵn.
    - Nhập trực tiếp sinh viên mới.
    - Nhập danh sách sinh viên từ file CSV.

- **Đăng nhập và điểm danh cho sinh viên**
  - Sinh viên đăng nhập và thực hiện điểm danh.
  - Hiển thị danh sách các buổi điểm danh đã thực hiện.

- **Quản lý điểm danh**
  - Giáo vụ xem và chỉnh sửa thông tin điểm danh của sinh viên.
  - Tạo báo cáo tổng hợp về tình trạng điểm danh theo từng môn học hoặc sinh viên.

- **Bảo mật và tính năng bổ sung**
  - Sinh viên phải đổi mật khẩu trong lần đăng nhập đầu tiên.
  - Mật khẩu được mã hóa và lưu trữ an toàn.
  - Kiểm tra tính hợp lệ của dữ liệu nhập trong tất cả các thao tác.
  - Giao diện tự động điều chỉnh theo kích thước cửa sổ ứng dụng.

## Công nghệ sử dụng

- **Ngôn ngữ lập trình:** Java (phiên bản 17)
- **Giao diện người dùng:** JavaFX
- **Cơ sở dữ liệu:** MySQL
- **Xây dựng và quản lý dự án:** Maven
- **Kiểm thử:** JUnit

## Cách cài đặt và sử dụng

1. **Clone repository:**  
   Tải mã nguồn về máy của bạn.
   
   ```bash
   git clone https://github.com/yourusername/DiemDanhSV.git


# Thành viên

Dưới đây là các thành viên tham gia vào dự án:

| Tên                        | Vai trò                            | GitHub               |
|----------------------------|------------------------------------|----------------------|
| Trần Nguyễn Thành Hiển     | Quản lý dự án, Attendance For teacher | @ThanhHienIT2004      |
  | Phạm Đăng Quang            | Attendance For student   | @QUANGNOTFUN          |
| Nguyễn Trường AN           | Login, logout                      | @TruongAn-28          |


