import React from 'react';
import './detailBook.css';

const DetailBook=()=>{
     const products = [
        {
            "_id": "1",
            "title": "Số đỏ",
            "src": ["https://307a0e78.vws.vegacdn.vn/view/v2/image/img.book/0/0/1/37100.jpg?v=1&w=340&h=497"],
            "author": "Vũ Trọng Phụng",
            "content": "Số đỏ là một tiểu thuyết văn học của nhà văn Vũ Trọng Phụng, đăng ở Hà Nội báo từ số 40 ngày 7 tháng 10 năm 1936 và được in thành sách lần đầu vào năm 1938. Nhiều nhân vật và câu nói trong tác phẩm đã đi vào cuộc sống đời thường và tác phẩm đã được dựng thành kịch, phim. Nhân vật chính của Số đỏ là Xuân - biệt danh là Xuân Tóc đỏ, từ chỗ là một kẻ bị coi là hạ lưu, bỗng nhảy lên tầng lớp danh giá của xã hội nhờ trào lưu Âu hóa của giới tiểu tư sản Hà Nội khi đó. Tác phẩm Số đỏ, cũng như các tác phẩm khác của Vũ Trọng Phụng đã từng bị cấm lưu hành tại Việt Nam Dân chủ Cộng hòa trước năm 1975 cũng như tại Việt Nam thống nhất từ năm 1975 cho đến năm 1986.",

        }
    ]
    return(
        <div className="app">
            {

                products.map(item =>(

                    <div className="details" key={item._id}>
                        <div className="big-img">
                            <img src={item.src} alt=""/>
                        </div>

                        <div className="box">
                            <div className="row">
                                <h2>{item.title}</h2>
                            </div>
                            <p>Tác giả: {item.author}</p>
                            <p>{item.content}</p>


                            <button className="cart">Trao đổi</button>

                        </div>
                    </div>
                ))
            }
        </div>
    );

}

export default DetailBook;