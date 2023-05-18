import {useEffect} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import {toast} from "react-toastify";


const VerificationPage = () => {
    const searchParams = new URLSearchParams(window.location.search);
    const token = searchParams.get("code");

    const navigate = useNavigate()
    console.log(token);
    useEffect(() => {
        const verify = async () => {
            try {
                const res = await axios.get(`http://localhost:7070/api/auth/verify?code=${token}`);
                if (res.status === 200) {
                    toast.success("Xác thực thành công");
                    navigate("/login");
                }

                console.log(res);
            } catch (error) {
                toast.error("Xác thực thất bại");
                navigate("/login");
                console.log(error);
            }
        }
        verify();
    }, [token]);

}
export default VerificationPage;