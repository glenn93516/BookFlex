<template>
  <div>
    <p 
      class="notice-message"
    >
      비밀번호를 찾으실 이메일 주소를 입력해주세요.
    </p>
    <b-form @submit.prevent="duplicateCheck('enter', email)">
      <b-form-input
        v-model="email"
        class="id-input"
        :class="emailStatus"
        placeholder="이메일 (example@gmail.com)"
        @blur="duplicateCheck('blur', email)"
      >
      </b-form-input>
      <div 
        class="warning-msg"
        v-show="emailStatus === 'is-invalid not-exist'"
      >
        존재하지 않는 회원입니다.
      </div>
      <div 
        class="warning-msg"
        v-show="emailStatus === 'is-invalid form-invalid'"
      >
        이메일 양식이 올바르지 않습니다.
      </div>
      <br>
      <b-button
        block
        class="btn btn-success login-button"
        type="submit"
        :disabled="disableBtn"
      >
        확인
      </b-button>
    </b-form>

    <div v-if="showModal">
      <div class="modal-mask">
        <div class="modal-wrapper">
          <div class="modal-container">

            <div class="modal_header" >
              <b-button 
                variant="light"
                @click="showModal = false"
              >
                X
              </b-button>
            </div>

            <div class="modal_body">
              <div style="font-size:30px; font-weight: bold;">비밀번호 초기화를 하시겠습니까?</div>
              <div style="color:red;">한 번 초기화한 비밀번호는 복구가 불가능합니다.</div>
            </div>

            <div class="modal_footer">
              <b-button 
                class="modal-button" 
                variant="primary"
                @click="goToPasswordCheckEmail"
              >
              예
              </b-button>
              <b-button 
                class="modal-button"
                variant="danger"
                @click="showModal = false"
                >
                아니오
                </b-button>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pageData: {
        progress: 0,
        size: "back-sm"
      },
      email: "",
      // 현재 이메일이 유효한지 아닌지 ("", is-valid, is-invalid form-invalid, is-invalid duplicate)
      emailStatus: "",
      // 버튼 활성화 여부 (활성화: false, 비활성화: true)
      disableBtn: true,
      showModal: false
    }
  },
  created() {

  },
  watch: {
    email() {
      this.isValidEmail()
    }
  },
  methods: {
    servePageInfo() {
      return this.pageData
    },
    // 이메일 형태 유효성 검사
    isValidEmail() {
      const reg_email = /^([0-9a-zA-Z_\\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
      if (this.email && !reg_email.test(this.email)) {
        this.emailStatus = "is-invalid form-invalid"
        this.disableBtn = true
      } else {
        if (this.email.length > 0) {
          this.emailStatus = ""
          this.disableBtn = false
        } else {
          this.emailStatus = ""
          this.disableBtn = true
        }
      }
    },
    duplicateCheck(input, email) {
      if (this.emailStatus !== 'is-invalid form-invalid') {
        const user = {userEmail: email}
        this.$axios.get(`${this.$store.getters.getServer}/user/check`, {
          params: user
        })
        .then(res => {
          // 가입 불가능, 초기화 가능
          if (!res.data.success) {
            this.emailStatus = ""
            this.disableBtn = false

            this.showModal = true
          } else {
            // 가입 가능, 초기화 불가능
            this.emailStatus = "is-invalid not-exist"
            this.diableBtn = true
          }
        })
        .catch(err => {
          console.error(err)
        })
      } else {
        console.log('여기에는 뭘 넣을까')
      }
    },
    goToPasswordCheckEmail() {
      const user = {userEmail: this.email}
      this.$store.dispatch("SetEmail", user)
      this.$router.push({ name: "PasswordCheckEmail" })
    }
  }
}
</script>

<style>
  .next-btn-submit{
    margin-top: 40px;
  }
  .form-control:focus{
    border-color: none;
    box-shadow: none;
  }
  .id-input{
    border: 0;
    border-bottom: 1px solid;
    border-radius: 0;
  }
  .warning-msg{
    color: red;
    margin-top: 8px;
    margin-bottom: -24px;
    font-size: 0.8rem;
  }

  .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .6);
    display: table;
    transition: opacity .3s ease;
  }
  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }
  .modal-container {
    width: 500px;
    height: 250px;
    margin: 0px auto;
    padding: 10px 20px 30px 20px;
    background-color: rgb(246, 246, 245);
    border-radius: 10px;
    transition: all .3s ease;
    
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }
  .modal_header {
    display: flex;
    flex-direction: row-reverse;
  }
  .modal_body {
    height: 100px;
    text-align: center;
    justify-content: center;
    align-items: center;
  }
  .modal_footer {
    display: flex;
    justify-content: space-around;
  }
  .modal-button {
    font-size: 20px;
    font-weight: bold;
    color: darkslategray;
    border-radius: 20px;
    width: 150px;
  }
</style>