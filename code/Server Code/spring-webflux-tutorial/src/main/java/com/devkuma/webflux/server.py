import socket
# 통신 정보 설정
IP = '172.17.2.139'
PORT = 5050
SIZE = 1024
ADDR = (IP, PORT)

# 서버 소켓 설정
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
    server_socket.bind(ADDR)
    server_socket.listen()

    # 무한루프 진입
    while True:
        client_socket, client_addr = server_socket.accept()
        msg = client_socket.recv(SIZE)
        print(msg.decode())

