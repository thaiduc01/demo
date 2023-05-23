Trong Spring Boot: 
- Liên kết chặt (tight coupling) là một kiểu thiết kế trong đó các thành phần của hệ thống được thiết kế để phụ thuộc chặt chẽ vào nhau. Điều này có nghĩa là khi một thành phần thay đổi, tất cả các thành phần khác cũng phải thay đổi để phù hợp với nó. 
- Ngược lại, liên kết lỏng (loose coupling) là một kiểu thiết kế trong đó các thành phần của hệ thống được thiết kế để không phụ thuộc chặt chẽ vào nhau. Điều này có nghĩa là khi một thành phần thay đổi, các thành phần khác không bị ảnh hưởng.
- Trong SQL, transaction là một chuỗi các hoạt động được thực hiện trên cơ sở dữ liệu. Một transaction bao gồm các hoạt động như chèn, cập nhật và xóa dữ liệu. Một transaction phải được bắt đầu bằng lệnh BEGIN TRANSACTION và kết thúc bằng lệnh COMMIT hoặc ROLLBACK1.
- Trong Hibernate, một session là một đối tượng được sử dụng để tương tác với cơ sở dữ liệu. Mỗi session đại diện cho một phiên làm việc với cơ sở dữ liệu. Session có thể được sử dụng để lưu trữ và truy xuất các đối tượng trong cơ sở dữ liệu. Session trong Hibernate có vòng đời như sau:
  
  + Transient: Đối tượng mới được tạo ra và chưa được liên kết với session.
  + Persistent: Đối tượng đã được liên kết với session và đã được lưu trữ trong cơ sở dữ liệu.
  + Detached: Đối tượng đã được liên kết với session nhưng không còn được quản lý bởi session nữa.
