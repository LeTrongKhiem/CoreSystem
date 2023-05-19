import "./auth.css"
import {Form, Formik} from "formik";
import * as Yup from "yup" ;
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";
import {toast} from "react-toastify";

const Register = () => {
    const navigate = useNavigate();

    const validationSchema = Yup.object().shape({
        email: Yup.string().required('Vui lòng nhập email').email('Email không hợp lệ').matches(/@st.hcmuaf.edu.vn$/, 'Email phải là địa chỉ @st.hcmuaf.edu.vn'),
        password: Yup.string()
            .required("Vui lòng nhập mật khẩu")
            .matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", "Mật khẩu phải có ít nhất 8 ký tự, 1 chữ hoa, 1 chữ thường, 1 số và 1 ký tự đặc biệt"),
        comfirmPassword: Yup.string()
            .required("Vui lòng nhập lại mật khẩu")
            .oneOf([Yup.ref('password'), null], 'Mật khẩu không khớp'),
        phoneNumber: Yup.string()
            .required("Vui lòng nhập số điện thoại")
            .matches("^[0-9]{10}$", "Số điện thoại không hợp lệ"),
        address: Yup.string()
            .required("Vui lòng nhập địa chỉ"),
        lastName: Yup.string()
            .required("Vui lòng nhập họ"),
        firstName: Yup.string()
            .required("Vui lòng nhập tên"),




    });
    const submit = (values) => {
        axios.post("http://localhost:7070/api/auth/register", values)
            .then(res => {
                    console.log(res);
                    toast.success("Đăng ký thành công");
                    navigate("/login");
                }
            )
            .catch(err => {
                    console.log(err);
                    toast.error("Đăng ký thất bại");

                }
            )


    }
    return (<div className="container register">
        <div className="login form">
            <header>Đăng ký</header>
            <Formik initialValues={{
                email: '', password: '', comfirmPassword: '', lastName: '',firstName: '', phoneNumber: '', address: '',
            }}
                    validationSchema={validationSchema}
                    onSubmit={values => {
                        submit(values);
                    }
                    }
            >
                {({values, errors, touched, handleChange, handleBlur, handleSubmit}) => {
                    console.log(values)
                    return (<Form>
                        <input type="email" placeholder="Email" name="email" onChange={handleChange}
                               onBlur={handleBlur}/>
                        <span className="errors">{errors.email}</span>
                        <input type="password" placeholder="Mật khẩu" name="password" onChange={handleChange}
                               onBlur={handleBlur}/>
                        <span className="errors">{errors.password}</span>
                        <input type="password" placeholder="Nhập lại mật khẩu" name="comfirmPassword"
                               onChange={handleChange}
                               onBlur={handleBlur}/>
                        <span className="errors">{errors.comfirmPassword}</span>
                        <input type="text" placeholder="Họ" name="lastName" onChange={handleChange}
                               onBlur={handleBlur}/>
                        <span className="errors">{errors.lastName}</span>
                        <input type="text" placeholder="Tên" name="firstName" onChange={handleChange}
                                 onBlur={handleBlur}/>
                        <span className="errors">{errors.firstName}</span>
                        <input type="text" placeholder="Số điện thoại" name="phoneNumber" onChange={handleChange}
                               onBlur={handleBlur}/>
                        <span className="errors">{errors.phoneNumber}</span>
                        <input type="text" placeholder="Địa chỉ" name="address" onChange={handleChange}/>
                        <span className="errors">{errors.address}</span>
                        <input type="submit" className="button" value="Đăng ký"/>
                    </Form>)
                }}
            </Formik>
            <div className="signup">
        <span className="signup">Bạn đã có tài khoản?
            <Link to="/login"><label>Đăng nhập</label></Link>

        </span>
            </div>
        </div>
    </div>)
}
export default Register;