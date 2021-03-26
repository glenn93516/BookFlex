<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">

          <div class="modal-header">
            <slot name="header">
              <div class="btn exit-font" @click="$emit('close-modal')">X</div>
            </slot>
          </div>

          <div class="modal-body">
            <slot name="body" :modalName="modalName">
              default body
            </slot>
          </div>

          <!-- <div class="modal-footer">
            <slot name="footer">
            </slot> -->
          <!-- </div> -->
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  data() {
    return {
      modalName: "Book"
    }
  }
}
</script>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 1000px;
  height: 800px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: rgba(255, 255, 255, 0.85);
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
  border-radius: 50px/ 50px;
}

.modal-header {
  margin-top: 0;
  color: #42b983;
  border-bottom: 0px;
  justify-content: flex-end;
  padding: 0px;
}

.exit-font {
  font-size: 1.5rem;
  color: rgb(95, 95, 95);
}

.modal-body {
  margin: 0px;
  padding-top: 0px;
}

.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>