import "./auth.css"
import {Form, Formik} from "formik";
import * as Yup from "yup" ;
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";
import {toast} from "react-toastify";
import {useDispatch} from "react-redux";
import {loginSuccess} from "../redux/slice/authSlice";

const Login = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const validationSchema = Yup.object().shape({
        email: Yup.string().required('Vui lòng nhập email').email('Email không hợp lệ').matches(/@st.hcmuaf.edu.vn$/, 'Email phải là địa chỉ @st.hcmuaf.edu.vn'),
        password: Yup.string()
            .required("Vui lòng nhập mật khẩu")
            .matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", "Mật khẩu phải có ít nhất 8 ký tự, 1 chữ hoa, 1 chữ thường, 1 số và 1 ký tự đặc biệt"),
    });
    const submit = (values) => {
        const token = localStorage.getItem("token");
        if (token) {
            delete axios.defaults.headers.common["Authorization"];
            //remove token from local storage
            localStorage.removeItem("token");
        }
        axios.post("http://localhost:7070/api/auth/authenticate", values)
            .then(res => {
                    console.log(res);
                    toast.success("Đăng nhập thành công");
                    localStorage.setItem("token", res.data);
                    dispatch(loginSuccess(res.data));
                    navigate("/");
                }
            )

            .catch(err => {
                    console.log(err);
                    toast.error("Đăng nhập thất bại");

                }
            )
    }


    return (<div className="container">
        <div className="login form">
            <header>Đăng nhập</header>
            <Formik initialValues={{
                email: '', password: ''
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
                        <Link href="#">Quên mật khẩu?</Link>
                        <input type="submit" className="button" value="Đăng nhập"/>
                    </Form>)
                }}
            </Formik>
            <div className="signup">
        <span className="signup">Bạn chưa có tài khoản?
         <Link to="/register"><label>Đăng ký</label></Link>
        </span>
            </div>
        </div>
    </div>)
}
export default Login;