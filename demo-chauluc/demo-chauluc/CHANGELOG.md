## May 13, 2023
### Các lưu ý
##### Tạo class riêng chứa dữ liệu cho việc thêm mới, chỉnh sửa dữ liệu (Class này chứa các validator dữ liệu thay vì dùng dto)

##### 1. Api thêm mới quốc gia
+ Lỗi: Thêm mới quốc gia bị lỗi 500 khi có hoặc không cung cấp một id châu lục bất kỳ
+ Điều kiện: Chưa thêm dữ liệu châu lục nào
+ Khắc phục:
    - Trường hợp bắt buộc phải có dữ liệu id châu lục > Kiểm tra có tồn tại châu lục với id đó hay không, nếu không thì trả về lỗi 400 với message không tồn tại châu lục với id được cung cấp
    - Trường hợp không bắt buộc id châu lục > Cho phép null và không lưu trường dữ liệu id châu lục ở db
+ Tình trạng: Cần khắc phục

##### 2. Validator Unique
+ Lấy dữ liệu từ database tương ứng cho mỗi loại entity để so sánh thay vì sử dụng arraylist ở java để lưu dữ liệu
+ Ví dụ:
    - Hai quốc gia không được có giống nhau nhưng quốc gia và ngôn ngữ có thể có mã giống nhau
    - Trường hợp restart lại ứng dụng (dùng dbms database) thì lúc này có thể thêm một quốc gia mới với mã đã tồn tại.
    - Trường hợp thêm một quốc gia trước đó bị lỗi > trong database không lưu lại dữ liệu > thêm lại quốc gia đó > lỗi trùng code.
+ Tình trạng: Cần khắc phục

##### 3. Api thêm mới Châu lục:
+ Lỗi: Dữ liệu id trả về lức thêm mới chưa đúng
+ Tình trạng: Cần khắc phục

##### 4. Lấy danh sách Châu lục:
+ Lỗi: Không có dữ liệu châu lục khi lấy danh sách
+ Tình trạng: Đã khắc phục

##### 5. Xóa châu lục
+ Lỗi: Đối với châu lục đang được sử dụng (mappring với quốc gia,..) thì lúc xóa bị 500 (lỗi ràng buộc)
+ Điều kiện: Có quốc gia khác đang dùng id châu lục này
+ Khắc phục:
    - Nếu là xóa cứng (hard delete): Cần kiểm tra điều kiện dữ liệu có đang được sử dụng ở bảng khác hay không
    - Hoặc đổi thành xóa mềm dữ liệu (soft delete)
+ Lưu ý: Dữ liệu đã xóa thì không còn khả dụng lúc lấy danh sách và lây thông tin chi tiết

##### Và các lỗi khác ...
##### Cần lưu ý sử dụng đúng annotation của mỗi thư viện, test tất cả các trường hợp sau khi thêm mỗi api mới