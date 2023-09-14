import { useEffect, useState } from "react";
import PageLayoutEmployee from "../components/PageLayoutEmployee";
import { Button, Table } from "antd";
import axios from "axios";
import { EditOutlined, DeleteOutlined } from "@ant-design/icons";
import AddEmployeesModal from "../components/AddEmployeesModal/index";
import dayjs from "dayjs";

const Employeelist = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [employees, setEmployees] = useState([]);
  const [initialValues, setInitialValues] = useState([]);
  const [search, setSearch] = useState("");

  const token=localStorage.getItem('TOKEN');

  const buttons = [
    {
      key: "addEmployees",
      text: "Add Employee",
      type: "primary",
      onClick: () => {
        setIsModalOpen(true);
        setInitialValues(null);
      },
    },
  ];

  const onOkAddModel = (values) => {
    setIsModalOpen(false);
    console.log(values);

    if (initialValues) {
      axios
        .put(`http://10.116.9.110:8090/api/v1/user/update-employee/${initialValues.email}`, {
          ...values,
        })
        .then((res) => {
          setEmployees((prevState) => {
            return prevState.map((u) => {
              if (res.data.email === u.email) {
                return {
                  ...res.data,
                };
              }
              return u;
            });
          });
        });
    } else {
      const newEmployees = JSON.stringify({
        name: values.name,
        surname: values.surname,
        birthday: values.birthday,
        birthdayPlace: values.birthdayPlace,
        email: values.email,
        phone: values.phone,
        address: values.address,
        idNumber: values.idNumber,
        //dateofemployment: values.dateofemployment,
        occupation: values.occupation,
        // companyname: values.companyname,
        // taxno: values.taxno,
        salary: values.salary,
        token: token
      });
      // axios
      //   .post("http://localhost:8090/api/v1/user/addemployee", newEmployees)
      //   .then((res) => {
      //     setEmployees((prevState) => [
      //       ...prevState,
      //       {
      //         ...res.data,
      //       },
      //     ]);
      //   });
      const header = {
        'Content-Type': 'application/json',
        // 'Accept-Encoding': 'gzip;q=1.0, compress;q=0.5',
      };
      axios({
        method: 'POST',
        url: 'http://10.116.9.110:8090/api/v1/user/addemployee',
        headers: header,
        data: newEmployees
      }).then(result => {
        console.log(result);
      }).catch(data => {
        const result = data.response.data;
        alert(result.message);
      })
    }
  };

  const onCancelAddModel = () => {
    setIsModalOpen(false);
  };

  
  const onClickEdit = (row) => {
    setIsModalOpen(true);
    row.establish=dayjs(row.establish)
    setInitialValues(row);
  };
  const onSearch = (value) => {
    setSearch(value);
  };

  const onDeleteEmployee = (employeeId) => {
    if (!employeeId) {
      console.error("Invalid Employee ID");
      return;
    }

    axios
      .delete(`http://localhost:5000/employees/${employeeId}`)
      .then((res) => {
        setEmployees((prevState) =>
          prevState.filter((employee) => employee.id !== employeeId)
        );
      })
      .catch((error) => {
        console.error("Delete error:", error);
      });
  };
  const columns = [
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
    },
    {
      title: "Surname",
      dataIndex: "surname",
      key: "surname",
    },
    {
      title: "Email",
      dataIndex: "email",
      key: "email",
    },
    {
      title: "Phone",
      dataIndex: "phone",
      key: "phone",
    },
    {
      title: "Occupation",
      dataIndex: "occupation",
      key: "occupation",
    },
    {
      title: "Address",
      dataIndex: "address",
      key: "address",
    },
    {
      dataIndex: "id",
      key: "id",
      render: (cell, row) => {
        return (
          <>
            <Button
              type="primary"
              shape="circle"
              icon={<EditOutlined />}
              onClick={() => onClickEdit(row)}
            />
            <Button
              type="danger"
              shape="circle"
              icon={<DeleteOutlined />}
              onClick={() => onDeleteEmployee(row.id)}
            />
          </>
        );
      },
      width: 100,
    },
  ];

  useEffect(() => {
    axios.get(`http://10.116.9.110:8090/api/v1/user/findall-employee?token=${token}`).then((res) => {
      setEmployees(res.data);
    });
  }, []);

  return (
    <PageLayoutEmployee buttons={buttons} onSearch={onSearch}>
      <Table
        dataSource={employees.filter((employee) => {
          return employee.name.includes(search);
        })}
        columns={columns}
        rowKey="id"
      />
      {isModalOpen && (
        <AddEmployeesModal
          isModalOpen={isModalOpen}
          onOk={onOkAddModel}
          onCancel={onCancelAddModel}
          initialValues={initialValues}
          
        />
      )}
    </PageLayoutEmployee>
  );
};

export default Employeelist;
